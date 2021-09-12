package uz.mk.onlinestoreapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.mk.onlinestoreapp.entity.Detail;
import uz.mk.onlinestoreapp.entity.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Integer> {
    List<Detail> findAllByOrderId(Integer order_id);
}
