package uz.mk.onlinestoreapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PaymentDTO {
    private Integer id;

    @NotNull(message = "Invoice ID is required")
    private Integer invoiceId;
}
