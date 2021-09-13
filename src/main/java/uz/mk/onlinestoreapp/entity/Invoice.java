package uz.mk.onlinestoreapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(optional = false)
    @JoinColumn(name = "ord_id")
    private Order order;

    @NotNull(message = "Invoice amount is required")
    @Column(precision = 8, scale = 2)
    private BigDecimal amount;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(nullable = false, updatable = false)
    private Date issued = new Date();

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(nullable = false)
    private Date due;

    public Invoice(Order order, BigDecimal amount) {
        this.order = order;
        this.amount = amount;
    }

    public Invoice(Order order, BigDecimal amount, Date due) {
        this.order = order;
        this.amount = amount;
        this.due = due;
    }

}
