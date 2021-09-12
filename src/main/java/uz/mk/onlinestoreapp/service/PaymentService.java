package uz.mk.onlinestoreapp.service;

import uz.mk.onlinestoreapp.entity.Detail;
import uz.mk.onlinestoreapp.entity.Payment;
import uz.mk.onlinestoreapp.payload.ApiResponse;
import uz.mk.onlinestoreapp.payload.PaymentDTO;

import java.util.List;

public interface PaymentService {
    ApiResponse saveOrEditPayment(PaymentDTO paymentDTO);

    ApiResponse deletePayment(Integer paymentId);

    List<Payment> getAllPayments();

    Payment getPaymentById(Integer paymentId);

}
