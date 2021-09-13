package uz.mk.onlinestoreapp.service;

import uz.mk.onlinestoreapp.entity.Customer;
import uz.mk.onlinestoreapp.entity.Invoice;
import uz.mk.onlinestoreapp.entity.Order;
import uz.mk.onlinestoreapp.entity.Product;
import uz.mk.onlinestoreapp.payload.InvoiceWithOrderDTO;

import java.util.List;

public interface DashboardService {
    List<Invoice> getExpiredInvoices();

    List<InvoiceWithOrderDTO> getWrongDateInvoices();

    List<Order> getOrdersWithoutDetails();

    List<Customer> getCustomersWithoutOrders();

    List<Customer> getCustomersLastOrders();

    List<Invoice> getOverpaidInvoices();

    List<Product> getHighDemandProducts();

    List<Product> getBulkProducts();

    List<Product> getNumberOfProductsInYear();

    List<Order> getOrdersWithoutInvoices();

}
