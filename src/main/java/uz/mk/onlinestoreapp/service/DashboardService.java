package uz.mk.onlinestoreapp.service;

import uz.mk.onlinestoreapp.entity.Customer;
import uz.mk.onlinestoreapp.entity.Invoice;
import uz.mk.onlinestoreapp.entity.Order;
import uz.mk.onlinestoreapp.entity.Product;
import uz.mk.onlinestoreapp.payload.CustomerWithOrderDTO;
import uz.mk.onlinestoreapp.payload.InvoiceWithOrderDTO;
import uz.mk.onlinestoreapp.payload.OverpaidInvoiceDTO;
import uz.mk.onlinestoreapp.projection.*;

import java.util.List;

public interface DashboardService {
    List<Invoice> getExpiredInvoices();

    List<InvoiceWithOrderDTO> getWrongDateInvoices();

    List<Order> getOrdersWithoutDetails();

    List<Customer> getCustomersWithoutOrders();

    List<CustomerWithOrderDTO> getCustomersLastOrders();

    List<OverpaidInvoice> getOverpaidInvoices();

    List<HighDemandProduct> getHighDemandProducts();

    List<BulkProduct> getBulkProducts();

    List<CountryWithCountOfOrder> getNumberOfProductsInYear();

    List<OrderWithDetails> getOrdersWithoutInvoices();

}
