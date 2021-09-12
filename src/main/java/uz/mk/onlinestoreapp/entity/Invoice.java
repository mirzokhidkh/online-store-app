package uz.mk.onlinestoreapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(optional = false)
    @JoinColumn(name="ord_id")
    private Order order;

    @NotBlank(message = "Invoice amount is required")
    @Column(precision = 8,scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date issued;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
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
