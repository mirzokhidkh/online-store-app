package uz.mk.onlinestoreapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.mk.onlinestoreapp.entity.Category;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(@NotBlank String name);
    boolean existsByNameAndIdNot(@NotBlank String name, Integer id);

    @Query(value = "select c.id, c.name\n" +
            "from product pr\n" +
            "         join category c on pr.category_id = c.id\n" +
            "where pr.id = ?",nativeQuery = true)
    Category findByProductId(Integer product_id);

}
