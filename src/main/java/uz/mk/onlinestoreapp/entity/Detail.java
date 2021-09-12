package uz.mk.onlinestoreapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.mk.onlinestoreapp.entity.template.AbsIntegerEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Detail extends AbsIntegerEntity {
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false)
    @JoinColumn(name = "ord_id")
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pr_id")
    private Product product;

    private short quantity;

    public Detail(Order order, Product product, short quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    @Transient
    private BigDecimal totalSumma;

    public BigDecimal getSumma() {
        totalSumma = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        return totalSumma;
    }
}
