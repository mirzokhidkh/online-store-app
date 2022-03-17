package uz.mk.onlinestoreapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mk.onlinestoreapp.entity.Customer;
import uz.mk.onlinestoreapp.payload.ApiResponse;
import uz.mk.onlinestoreapp.service.CustomerService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public HttpEntity<?> saveOrEditCustomer(@Valid @RequestBody Customer customer) {
        ApiResponse apiResponse = customerService.saveOrEditCustomer(customer);
        return ResponseEntity.status(apiResponse.isStatus() ? 201 : 409).body(apiResponse);
    }

    @DeleteMapping("/{customer_id}")
    public HttpEntity<?> deleteCustomer(@PathVariable Integer customer_id) {
        ApiResponse apiResponse = customerService.deleteCustomer(customer_id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/list")
    public Iterable<Customer>  getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/details/{id}")
    public HttpEntity<?> getCustomerById(@PathVariable(name = "id") Integer customer_id) {
        Customer customer = customerService.getCustomerById(customer_id);
        return ResponseEntity.ok(customer);
    }

}
