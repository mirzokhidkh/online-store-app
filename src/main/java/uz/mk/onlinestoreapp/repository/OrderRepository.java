package uz.mk.onlinestoreapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.mk.onlinestoreapp.entity.Order;
import uz.mk.onlinestoreapp.projection.OrderWithDetails;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "select * from orders o where o.date < '2016-09-06'", nativeQuery = true)
    List<Order> findOrdersWithoutDetails();


    @Query(value = "select o.id as orderId, o.date, i.amount  as totalPrice\n" +
            "from orders o\n" +
            "         join invoice i on o.id = i.ord_id\n" +
            "    except\n" +
            "select o.id, o.date, i.amount\n" +
            "from orders o\n" +
            "         right join invoice i on o.id = i.ord_id\n" +
            "         right join payment p on i.id = p.inv_id\n", nativeQuery = true)
    List<OrderWithDetails> getOrdersWithoutInvoices();


}
