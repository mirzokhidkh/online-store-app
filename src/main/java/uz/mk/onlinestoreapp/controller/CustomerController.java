package uz.mk.onlinestoreapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.mk.onlinestoreapp.entity.Customer;
import uz.mk.onlinestoreapp.entity.Product;
import uz.mk.onlinestoreapp.payload.ApiResponse;
import uz.mk.onlinestoreapp.service.CustomerService;
import uz.mk.onlinestoreapp.service.MapValidationErrorService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final MapValidationErrorService mapValidationErrorService;

    @Autowired
    public CustomerController(CustomerService customerService, MapValidationErrorService mapValidationErrorService) {
        this.customerService = customerService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping
    public HttpEntity<?> saveOrEditCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
        HttpEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap!=null) {
            return errorMap;
        }
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

    @GetMapping("/details")
    public HttpEntity<?> getProductById(@RequestParam Integer customer_id) {
        Customer customer = customerService.getCustomerById(customer_id);
        return ResponseEntity.ok(customer);
    }

}
