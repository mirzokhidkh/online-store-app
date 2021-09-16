package uz.mk.onlinestoreapp.payload;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PaymentDTO {
    private Integer id;

    @NotNull(message = "Invoice ID is required")
    private Integer invoiceId;

    @NotNull(message = "Product amount is required")
    @Min(0)
    private BigDecimal amount;
}
