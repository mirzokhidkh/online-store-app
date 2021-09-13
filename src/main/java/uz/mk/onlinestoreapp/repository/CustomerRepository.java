package uz.mk.onlinestoreapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.mk.onlinestoreapp.entity.Category;
import uz.mk.onlinestoreapp.entity.Customer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByPhone(@Size(min = 7, max = 50, message = "Please use 7 to 50 characters") String phone);
    boolean existsByPhoneAndIdNot(@Size(min = 7, max = 50, message = "Please use 7 to 50 characters") String phone, Integer id);

    @Query(value = "select distinct c.id, c.name, c.country, c.address, c.phone, c.created_at, c.updated_at \n" +
            "from  customer c join orders o on o.cust_id=c.id \n" +
            "where o.date not between '2016-01-01' and '2016-12-31'",nativeQuery = true)
    List<Customer> findCustomersWithoutOrders();
}
