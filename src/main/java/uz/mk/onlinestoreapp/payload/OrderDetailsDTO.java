package uz.mk.onlinestoreapp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailsDTO {
    private Integer id;

    @NotNull(message = "Customer ID is required")
    private Integer customer_id;

    @NotNull(message = "Product ID is required")
    private Integer product_id;

    @NotNull(message = "Detail quantity is required")
    @Min(1)
    private Short quantity;

    private String productName;

    private BigDecimal totalSumma;

}
