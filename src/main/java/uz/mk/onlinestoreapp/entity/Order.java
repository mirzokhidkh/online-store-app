package uz.mk.onlinestoreapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.mk.onlinestoreapp.entity.template.AbsIntegerEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
@AttributeOverride(
        name = "createdAt",
        column = @Column(name = "date")
)
public class Order extends AbsIntegerEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name="cust_id")
    private Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Detail> detailList;

    public Order(Customer customer) {
        this.customer = customer;
    }
}
