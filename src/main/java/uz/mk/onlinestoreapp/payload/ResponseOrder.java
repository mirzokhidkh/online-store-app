package uz.mk.onlinestoreapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrder {
    private String status;

    private Integer invoiceNumber;
}
