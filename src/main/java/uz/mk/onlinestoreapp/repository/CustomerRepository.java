package uz.mk.onlinestoreapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.mk.onlinestoreapp.entity.Category;
import uz.mk.onlinestoreapp.entity.Customer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByPhone(@Size(min = 7, max = 50, message = "Please use 7 to 50 characters") String phone);
    boolean existsByPhoneAndIdNot(@Size(min = 7, max = 50, message = "Please use 7 to 50 characters") String phone, Integer id);

}
