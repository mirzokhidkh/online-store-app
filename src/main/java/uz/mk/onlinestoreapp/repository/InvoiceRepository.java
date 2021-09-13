package uz.mk.onlinestoreapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.mk.onlinestoreapp.entity.Invoice;
import uz.mk.onlinestoreapp.entity.Order;
import uz.mk.onlinestoreapp.payload.InvoiceWithOrderDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Optional<Invoice> findByOrderId(Integer order_id);

    @Query(value = "select *from invoice inv where inv.issued>inv.due", nativeQuery = true)
    List<Invoice> findExpiredInvoices();

    @Query(value = "select new uz.mk.onlinestoreapp.payload.InvoiceWithOrderDTO(" +
            "i.id," +
            "i.issued," +
            "i.order.id," +
            "i.order.createdAt" +
            ")\n" +
            "from Invoice i  where i.issued<i.order.createdAt")
    List<InvoiceWithOrderDTO> findWrongDateInvoices();

}
