package uz.mk.onlinestoreapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.mk.onlinestoreapp.entity.template.AbsIntegerEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsIntegerEntity {
    @Size(min = 3, max = 10)
    @Column(nullable = false, length = 10)
    private String name;

    @ManyToOne(optional = false)
    private Category category;

    @Column(length = 20)
    private String description;

    @NotBlank
    @Column(precision = 6, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, length = 1024)
    private String photo;
}
