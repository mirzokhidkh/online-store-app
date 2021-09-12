package uz.mk.onlinestoreapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.mk.onlinestoreapp.entity.Customer;
import uz.mk.onlinestoreapp.entity.Product;

import javax.validation.constraints.Size;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByNameAndCategoryId(@Size(min = 3, max = 10) String name, Integer category_id);
    boolean existsByNameAndCategoryIdAndIdNot(@Size(min = 3, max = 10) String name, Integer category_id, Integer id);

}
