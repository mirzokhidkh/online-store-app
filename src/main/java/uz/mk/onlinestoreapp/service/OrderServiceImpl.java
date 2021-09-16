package uz.mk.onlinestoreapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.mk.onlinestoreapp.entity.Detail;
import uz.mk.onlinestoreapp.entity.Invoice;
import uz.mk.onlinestoreapp.entity.Order;
import uz.mk.onlinestoreapp.exception.ResourceNotFoundException;
import uz.mk.onlinestoreapp.payload.ApiResponse;
import uz.mk.onlinestoreapp.payload.OrderDetailsDTO;
import uz.mk.onlinestoreapp.payload.ResponseOrder;
import uz.mk.onlinestoreapp.repository.*;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Transactional
    @Override
    public ResponseOrder saveOrEditOrder(OrderDetailsDTO orderDetailsDTO) {
        boolean isIdNull = orderDetailsDTO.getId() == null;

        Order order;
        if (!isIdNull) {
            order = orderRepository.findById(orderDetailsDTO.getId()).
                    orElseThrow(() -> new ResourceNotFoundException("Order with ID '" + orderDetailsDTO.getId() + "' does not found"));
        } else {
            order = createOrder(orderDetailsDTO.getCustomer_id());
        }
        Order savedOrEditedOrder = orderRepository.save(order);
        Detail detail = createDetail(orderDetailsDTO, savedOrEditedOrder);
        detailRepository.save(detail);

        List<Detail> detailList = detailRepository.findAllByOrderId(savedOrEditedOrder.getId());
        BigDecimal totalAmount = detailList.stream().map(Detail::getSumma).reduce(BigDecimal.ZERO, BigDecimal::add);
        Invoice invoice;
        if (!isIdNull) {
            invoice = invoiceRepository.findByOrderId(savedOrEditedOrder.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("FAILED! Invoice does not found"));
            invoice.setAmount(totalAmount);
        } else {
            invoice = createInvoice(savedOrEditedOrder, totalAmount);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(invoice.getIssued());
            calendar.add(Calendar.DATE, 7);
            invoice.setDue(calendar.getTime());
        }
        invoiceRepository.save(invoice);

        return new ResponseOrder("SUCCESS", invoice.getId());
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
    public List<OrderDetailsDTO> getOrderDetailsById(Integer order_id) {
        List<Detail> detailList = detailRepository.findAllByOrderId(order_id);
        List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();
        detailList.forEach(detail -> {
            orderDetailsDTOList.add(mapDetailToOrderDetailsDTO(detail));
        });
        return orderDetailsDTOList;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    private Order createOrder(Integer customerId) {
        Order order = new Order();
        order.setCustomer(customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer with ID '" + customerId + "' not found")));
        return order;
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

    private OrderDetailsDTO mapDetailToOrderDetailsDTO(Detail detail) {
        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
        orderDetailsDTO.setId(detail.getId());
        orderDetailsDTO.setProductName(detail.getProduct().getName());
        orderDetailsDTO.setQuantity(detail.getQuantity());
        orderDetailsDTO.setTotalPrice(detail.getSumma());
        return orderDetailsDTO;
    }


}
