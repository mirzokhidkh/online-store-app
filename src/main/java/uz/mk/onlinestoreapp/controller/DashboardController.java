package uz.mk.onlinestoreapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.mk.onlinestoreapp.entity.Customer;
import uz.mk.onlinestoreapp.entity.Invoice;
import uz.mk.onlinestoreapp.entity.Order;
import uz.mk.onlinestoreapp.payload.CustomerWithOrderDTO;
import uz.mk.onlinestoreapp.payload.InvoiceWithOrderDTO;
import uz.mk.onlinestoreapp.payload.OverpaidInvoiceDTO;
import uz.mk.onlinestoreapp.projection.*;
import uz.mk.onlinestoreapp.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }


    @GetMapping("/expired_invoices")
    public Iterable<Invoice> getExpiredInvoices() {
        return dashboardService.getExpiredInvoices();
    }

    @GetMapping("/wrong_date_invoices")
    public Iterable<InvoiceWithOrderDTO> getWrongDateInvoices() {
        return dashboardService.getWrongDateInvoices();
    }

    @GetMapping("/orders_without_details")
    public Iterable<Order> getOrdersWithoutDetails() {
        return dashboardService.getOrdersWithoutDetails();
    }

    @GetMapping("/customers_without_orders")
    public Iterable<Customer> getCustomersWithoutOrders() {
        return dashboardService.getCustomersWithoutOrders();
    }

    @GetMapping("/customer_last_orders")
    public Iterable<CustomerWithOrderDTO> getCustomersLastOrders() {
        return dashboardService.getCustomersLastOrders();
    }

    @GetMapping("/overpaid_invoices")
    public Iterable<OverpaidInvoice> getOverpaidInvoices() {
        return dashboardService.getOverpaidInvoices();
    }


    @GetMapping("/high_demand_products")
    public Iterable<HighDemandProduct> getHighDemandProducts() {
        return dashboardService.getHighDemandProducts();
    }

    @GetMapping("/bulk_products")
    public Iterable<BulkProduct> getBulkProducts() {
        return dashboardService.getBulkProducts();
    }

    @GetMapping("/number_of_products_in_year")
    public Iterable<CountryWithCountOfOrder> getNumberOfProductsInYear() {
        return dashboardService.getNumberOfProductsInYear();
    }

    @GetMapping("/orders_without_invoices")
    public Iterable<OrderWithDetails> getOrdersWithoutInvoices() {
        return dashboardService.getOrdersWithoutInvoices();
    }



}
