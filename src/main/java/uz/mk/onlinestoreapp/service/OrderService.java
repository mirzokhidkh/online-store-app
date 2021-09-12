package uz.mk.onlinestoreapp.service;

import uz.mk.onlinestoreapp.entity.Detail;
import uz.mk.onlinestoreapp.entity.Order;
import uz.mk.onlinestoreapp.payload.ApiResponse;
import uz.mk.onlinestoreapp.payload.OrderDetailsDTO;

import java.util.List;

public interface OrderService {
    ApiResponse saveOrEditOrder(OrderDetailsDTO orderDetailsDTO);

    ApiResponse deleteOrder(Integer order_id);

    List<Order> getAllOrders();

    List<Detail> getOrderDetailsById(Integer order_id);

}
