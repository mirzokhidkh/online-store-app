package uz.mk.onlinestoreapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.mk.onlinestoreapp.entity.template.AbsIntegerEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice extends AbsIntegerEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name="ord_id")
    private Order order;

    @NotBlank
    @Column(precision = 8,scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private Date issued;

    @Column(nullable = false)
    private Date due;
}
