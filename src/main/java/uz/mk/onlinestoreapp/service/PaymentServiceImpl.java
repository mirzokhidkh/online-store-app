package uz.mk.onlinestoreapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.mk.onlinestoreapp.entity.Invoice;
import uz.mk.onlinestoreapp.entity.Payment;
import uz.mk.onlinestoreapp.exception.ResourceNotFoundException;
import uz.mk.onlinestoreapp.payload.ApiResponse;
import uz.mk.onlinestoreapp.payload.PaymentDTO;
import uz.mk.onlinestoreapp.repository.InvoiceRepository;
import uz.mk.onlinestoreapp.repository.PaymentRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;


    @Override
    public ApiResponse saveOrEditPayment(PaymentDTO paymentDTO) {
        boolean isIdNull = paymentDTO.getId() == null;
        Invoice invoice = invoiceRepository.findById(paymentDTO.getInvoiceId()).orElseThrow(() -> new ResourceNotFoundException("FAILED ! Invoice with ID '" + paymentDTO.getInvoiceId() + "' not found"));
        Payment payment = createPayment(invoice, invoice.getAmount());
        if (!isIdNull) {
            payment.setId(paymentDTO.getId());
        }
        Payment savedOrEditedPayment = paymentRepository.save(payment);
        return new ApiResponse("SUCCESS",true, savedOrEditedPayment);
    }

    @Override
    public ApiResponse deletePayment(Integer paymentId) {
        try {
            paymentRepository.deleteById(paymentId);
            return new ApiResponse("Payment with ID '" + paymentId + " was deleted", true);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cannot Payment with ID '" + paymentId + "'. This Payment does not found");
        }
    }

    @Override
    public Payment getPaymentById(Integer paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow(() -> new ResourceNotFoundException("Payment with ID '" + paymentId + "' not found"));
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }


    private Payment createPayment(Invoice invoice, BigDecimal amount) {
        return new Payment(amount, invoice);
    }


}
