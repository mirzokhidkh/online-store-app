package uz.mk.onlinestoreapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.mk.onlinestoreapp.entity.Detail;
import uz.mk.onlinestoreapp.entity.Invoice;
import uz.mk.onlinestoreapp.entity.Order;
import uz.mk.onlinestoreapp.exception.ResourceNotFoundException;
import uz.mk.onlinestoreapp.payload.ApiResponse;
import uz.mk.onlinestoreapp.payload.OrderDetailsDTO;
import uz.mk.onlinestoreapp.payload.ResponseOrder;
import uz.mk.onlinestoreapp.repository.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DetailRepository detailRepository;
    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    public ResponseOrder saveOrEditOrder(OrderDetailsDTO orderDetailsDTO) {
        boolean isIdNull = orderDetailsDTO.getId() == null;

        Order order = createOrderAndDetail(orderDetailsDTO.getCustomer_id());
        if (!isIdNull) {
            order.setId(orderDetailsDTO.getId());
        }
        Order savedOrEditedOrder = orderRepository.save(order);
        Detail detail = createDetail(orderDetailsDTO, savedOrEditedOrder);
        detailRepository.save(detail);

        List<Detail> detailList = getOrderDetailsById(savedOrEditedOrder.getId());
        BigDecimal totalAmount = detailList.stream().map(Detail::getSumma).reduce(BigDecimal.ZERO, BigDecimal::add);

        Invoice invoice = createInvoice(savedOrEditedOrder, totalAmount);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(invoice.getIssued());
        calendar.add(Calendar.DATE, 7);
        invoice.setDue(calendar.getTime());
        invoiceRepository.save(invoice);

        return new ResponseOrder(isIdNull ? "SUCCESS" : "FAILED", invoice.getId());
    }

    @Override
    public ApiResponse deleteOrder(Integer order_id) {
        try {
            orderRepository.deleteById(order_id);
            return new ApiResponse("Order with ID '" + order_id + " was deleted", true);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cannot Order with ID '" + order_id + "'. This Order does not found");
        }
    }

    @Override
    public List<Detail> getOrderDetailsById(Integer order_id) {
        return detailRepository.findAllByOrderId(order_id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    private Order createOrderAndDetail(Integer customerId) {
        return new Order(customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer with ID '" + customerId + "' not found")));
    }

    private Detail createDetail(OrderDetailsDTO orderDetailsDTO, Order order) {
        return new Detail(
                order,
                productRepository.findById(orderDetailsDTO.getProduct_id()).orElseThrow(() -> new ResourceNotFoundException("Product with ID '" + orderDetailsDTO.getProduct_id() + "' not found")),
                orderDetailsDTO.getQuantity()
        );
    }

    private Invoice createInvoice(Order order, BigDecimal amount) {
        return new Invoice(order, amount);
    }


}
