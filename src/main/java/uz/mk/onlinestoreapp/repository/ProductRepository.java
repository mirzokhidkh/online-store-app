package uz.mk.onlinestoreapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.mk.onlinestoreapp.entity.Product;
import uz.mk.onlinestoreapp.projection.BulkProduct;
import uz.mk.onlinestoreapp.projection.HighDemandProduct;

import javax.validation.constraints.Size;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByNameAndCategoryId(@Size(min = 3, max = 10) String name, Integer category_id);
    boolean existsByNameAndCategoryIdAndIdNot(@Size(min = 3, max = 10) String name, Integer category_id, Integer id);


    @Query(value = "select p.id as productId , count(p.id)\n as countOfOrdered\n" +
            "from Product p\n" +
            "         join Detail d on p.id = d.product.id\n" +
            "group by p.id\n" +
            "having count(p.id)>=10")
    List<HighDemandProduct> getHighDemandProducts();

    @Query(value = "select p.id as productId, p.price as price\n" +
            "from Product p\n" +
            "         join Detail d on p.id = d.product.id\n" +
            "group by p.id\n" +
            "having count(d.quantity) >= 8")
    List<BulkProduct> getBulkProducts();
}
