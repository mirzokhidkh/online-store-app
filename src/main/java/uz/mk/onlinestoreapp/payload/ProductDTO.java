package uz.mk.onlinestoreapp.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Integer id;

    @Size(min = 3, max = 10,message = "Please use 3 to 10 characters")
    private String name;

    @NotNull(message = "Category ID is required")
    private Integer category_id;

    @NotBlank(message = "Product description is required")
    @Length(max = 20,message = "Please use to 20 characters")
    private String description;

    @Digits(integer = 4,fraction = 2,message = "Numeric value out of bounds (<4 digits>.<2 digits> expected)")
    private BigDecimal price;

    @NotBlank(message = "Product photo is required")
    private String photo;
}
