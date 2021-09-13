package uz.mk.onlinestoreapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.mk.onlinestoreapp.entity.*;
import uz.mk.onlinestoreapp.exception.ResourceNotFoundException;
import uz.mk.onlinestoreapp.payload.ApiResponse;
import uz.mk.onlinestoreapp.payload.InvoiceWithOrderDTO;
import uz.mk.onlinestoreapp.repository.CategoryRepository;
import uz.mk.onlinestoreapp.repository.CustomerRepository;
import uz.mk.onlinestoreapp.repository.InvoiceRepository;
import uz.mk.onlinestoreapp.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final InvoiceRepository invoiceRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;



    @Override
    public List<Invoice> getExpiredInvoices() {
        return invoiceRepository.findExpiredInvoices();
    }

    @Override
    public List<InvoiceWithOrderDTO> getWrongDateInvoices() {
        return invoiceRepository.findWrongDateInvoices();
    }

    @Override
    public List<Order> getOrdersWithoutDetails() {
        return orderRepository.findOrdersWithoutDetails();
    }

    @Override
    public List<Customer> getCustomersWithoutOrders() {
        return customerRepository.findCustomersWithoutOrders();
    }

    @Override
    public List<Customer> getCustomersLastOrders() {
        return null;
    }

    @Override
    public List<Invoice> getOverpaidInvoices() {
        return null;
    }

    @Override
    public List<Product> getHighDemandProducts() {
        return null;
    }

    @Override
    public List<Product> getBulkProducts() {
        return null;
    }

    @Override
    public List<Product> getNumberOfProductsInYear() {
        return null;
    }

    @Override
    public List<Order> getOrdersWithoutInvoices() {
        return null;
    }
}
