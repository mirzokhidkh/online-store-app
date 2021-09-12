package uz.mk.onlinestoreapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.mk.onlinestoreapp.entity.Customer;
import uz.mk.onlinestoreapp.entity.Product;
import uz.mk.onlinestoreapp.exception.ResourceNotFoundException;
import uz.mk.onlinestoreapp.payload.ApiResponse;
import uz.mk.onlinestoreapp.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public ApiResponse saveOrEditCustomer(Customer customer) {
        if (customer.getId() == null) {
            if (customerRepository.existsByPhone(customer.getName())) {
                return new ApiResponse("Customer with such a phone number '" + customer.getPhone() + "' already exists", false);
            }
            Customer savedCustomer = customerRepository.save(customer);
            return new ApiResponse("Customer saved", true, savedCustomer);
        } else {
            if (customerRepository.existsByPhoneAndIdNot(customer.getName(), customer.getId())) {
                return new ApiResponse("Customer with such a phone number '" + customer.getPhone() + "' already exists", false);
            }
            Customer editedCustomer = customerRepository.save(customer);
            return new ApiResponse("Customer ID '" + customer.getId() + "' edited", true, editedCustomer);
        }
    }


    @Override
    public ApiResponse deleteCustomer(Integer customer_id) {
        try {
            customerRepository.deleteById(customer_id);
            return new ApiResponse("Customer with ID '" + customer_id + " was deleted", true);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cannot Customer with ID '" + customer_id + "'. This customer does not found");
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Integer customer_id) {
        return customerRepository.findById(customer_id).orElseThrow(() -> new ResourceNotFoundException("Csutomer with ID '"+customer_id+"' not found"));
    }

}
