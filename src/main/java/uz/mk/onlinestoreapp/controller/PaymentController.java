package uz.mk.onlinestoreapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.mk.onlinestoreapp.entity.Payment;
import uz.mk.onlinestoreapp.payload.ApiResponse;
import uz.mk.onlinestoreapp.payload.PaymentDTO;
import uz.mk.onlinestoreapp.service.MapValidationErrorService;
import uz.mk.onlinestoreapp.service.PaymentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final MapValidationErrorService mapValidationErrorService;

    @Autowired
    public PaymentController(PaymentService PaymentService, MapValidationErrorService mapValidationErrorService) {
        this.paymentService = PaymentService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping
    public HttpEntity<?> saveOrEditPayment(@Valid @RequestBody PaymentDTO paymentDTO, BindingResult result) {
        HttpEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }
        ApiResponse apiResponse = paymentService.saveOrEditPayment(paymentDTO);
        return ResponseEntity.status(apiResponse.getMessage().equals("SUCCESS") ? 201 : 409).body(apiResponse);
    }

    @DeleteMapping("/{payment_id}")
    public HttpEntity<?> deletePayment(@PathVariable Integer payment_id) {
        ApiResponse apiResponse = paymentService.deletePayment(payment_id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/list")
    public Iterable<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }


    @GetMapping("/details")
    public HttpEntity<?> getPaymentById(@RequestParam Integer payment_id) {
        Payment payment = paymentService.getPaymentById(payment_id);
        return ResponseEntity.ok(payment);
    }

}
