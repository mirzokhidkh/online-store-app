package uz.mk.onlinestoreapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.mk.onlinestoreapp.entity.Detail;
import uz.mk.onlinestoreapp.entity.Order;
import uz.mk.onlinestoreapp.payload.ApiResponse;
import uz.mk.onlinestoreapp.payload.OrderDetailsDTO;
import uz.mk.onlinestoreapp.service.MapValidationErrorService;
import uz.mk.onlinestoreapp.service.OrderService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    private final MapValidationErrorService mapValidationErrorService;

    @Autowired
    public OrderController(OrderService OrderService, MapValidationErrorService mapValidationErrorService) {
        this.orderService = OrderService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping
    public HttpEntity<?> saveOrEditOrder(@Valid @RequestBody OrderDetailsDTO orderDetailsDTO, BindingResult result) {
        HttpEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap!=null) {
            return errorMap;
        }
        ApiResponse apiResponse = orderService.saveOrEditOrder(orderDetailsDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 201 : 409).body(apiResponse);
    }

    @DeleteMapping("/{order_id}")
    public HttpEntity<?> deleteOrder(@PathVariable Integer order_id) {
        ApiResponse apiResponse = orderService.deleteOrder(order_id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/list")
    public Iterable<Order>  getAllOrders(){
        return orderService.getAllOrders();
    }


    @GetMapping("/details")
    public HttpEntity<?> getOrderDetailsById(@RequestParam Integer order_id) {
        List<Detail> orderDetailList = orderService.getOrderDetailsById(order_id);
        return ResponseEntity.ok(orderDetailList);
    }

}
