package uz.mk.onlinestoreapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.mk.onlinestoreapp.entity.Customer;
import uz.mk.onlinestoreapp.entity.Invoice;
import uz.mk.onlinestoreapp.entity.Order;
import uz.mk.onlinestoreapp.payload.InvoiceWithOrderDTO;
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
}
