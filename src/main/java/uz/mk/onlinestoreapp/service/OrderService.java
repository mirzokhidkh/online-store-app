package uz.mk.onlinestoreapp.service;

import uz.mk.onlinestoreapp.entity.Detail;
import uz.mk.onlinestoreapp.entity.Order;
import uz.mk.onlinestoreapp.payload.ApiResponse;
import uz.mk.onlinestoreapp.payload.OrderDetailsDTO;
import uz.mk.onlinestoreapp.payload.ResponseOrder;

import java.util.List;

public interface OrderService {
    ResponseOrder saveOrEditOrder(OrderDetailsDTO orderDetailsDTO);

    ApiResponse deleteOrder(Integer order_id);

    List<Order> getAllOrders();

    List<OrderDetailsDTO> getOrderDetailsById(Integer order_id);

}
