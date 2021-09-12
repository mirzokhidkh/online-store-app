package uz.mk.onlinestoreapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import uz.mk.onlinestoreapp.entity.template.AbsIntegerEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    private Timestamp time;

    @NotBlank(message = "Payment amount is required")
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
