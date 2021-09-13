package uz.mk.onlinestoreapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import uz.mk.onlinestoreapp.entity.template.AbsIntegerEntity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp time;

    @NotNull(message = "Payment amount is required")
    @Digits(integer = 6,fraction = 2,message = "Numeric value out of bounds (<6 digits>.<2 digits> expected)")
    @Column(precision = 8, scale = 2)
    private BigDecimal amount;

    @OneToOne(optional = false)
    @JoinColumn(name="inv_id")
    private Invoice invoice;

    public Payment(BigDecimal amount, Invoice invoice) {
        this.amount = amount;
        this.invoice = invoice;
    }
}
