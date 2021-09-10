package uz.mk.onlinestoreapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import uz.mk.onlinestoreapp.entity.template.AbsIntegerEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer extends AbsIntegerEntity {
    @Size(min = 3, max = 14)
    @Column(nullable = false,length = 14)
    private String name;

    @Size(min = 2, max = 3)
    @Column(nullable = false,length = 3)
    private String country;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Size(min = 7, max = 50)
    @Column(length = 50)
    private String phone;
}
