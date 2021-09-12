package uz.mk.onlinestoreapp.service;

import uz.mk.onlinestoreapp.entity.Customer;
import uz.mk.onlinestoreapp.entity.Product;
import uz.mk.onlinestoreapp.payload.ApiResponse;

import java.util.List;

public interface CustomerService {
    ApiResponse saveOrEditCustomer(Customer customer);

    ApiResponse deleteCustomer(Integer customer_id);

    List<Customer> getAllCustomers();

    Customer getCustomerById(Integer customer_id);

}
