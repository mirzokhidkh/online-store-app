package uz.mk.onlinestoreapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.mk.onlinestoreapp.entity.*;
import uz.mk.onlinestoreapp.payload.CustomerWithOrderDTO;
import uz.mk.onlinestoreapp.payload.InvoiceWithOrderDTO;
import uz.mk.onlinestoreapp.projection.*;
import uz.mk.onlinestoreapp.repository.CustomerRepository;
import uz.mk.onlinestoreapp.repository.InvoiceRepository;
import uz.mk.onlinestoreapp.repository.OrderRepository;
import uz.mk.onlinestoreapp.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final InvoiceRepository invoiceRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;


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
        return customerRepository.getCustomersWithoutOrders();
    }

    @Override
    public List<CustomerWithOrderDTO> getCustomersLastOrders() {
        return customerRepository.getCustomersLastOrders();
    }

    @Override
    public List<OverpaidInvoice> getOverpaidInvoices() {
        return invoiceRepository.findOverpaidInvoices();
    }

    @Override
    public List<HighDemandProduct> getHighDemandProducts() {
        return productRepository.getHighDemandProducts();
    }

    @Override
    public List<BulkProduct> getBulkProducts() {
        return productRepository.getBulkProducts();
    }

    @Override
    public List<CountryWithCountOfOrder> getNumberOfProductsInYear() {
        return customerRepository.getNumberOfProductsInYear();
    }

    @Override
    public List<OrderWithDetails> getOrdersWithoutInvoices() {
        return orderRepository.getOrdersWithoutInvoices();
    }
}
