package uz.mk.onlinestoreapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.mk.onlinestoreapp.entity.Invoice;
import uz.mk.onlinestoreapp.entity.Order;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

}
