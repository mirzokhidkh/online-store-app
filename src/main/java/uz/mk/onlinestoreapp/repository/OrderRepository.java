package uz.mk.onlinestoreapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.mk.onlinestoreapp.entity.Order;
import uz.mk.onlinestoreapp.entity.Product;

import javax.validation.constraints.Size;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
