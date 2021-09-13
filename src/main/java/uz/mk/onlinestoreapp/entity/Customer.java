package uz.mk.onlinestoreapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import uz.mk.onlinestoreapp.entity.template.AbsIntegerEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer extends AbsIntegerEntity {

    @NotBlank(message = "Customer name is required")
    @Size(min = 3, max = 14, message = "Please use 3 to 14 characters")
    @Column(nullable = false, length = 14)
    private String name;

    @NotBlank(message = "Customer country is required")
    @Size(min = 2, max = 3)
    @Column(nullable = false, length = 3)
    private String country;

    @NotBlank(message = "Customer address is required")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String address;

    @NotBlank(message = "Customer phone number is required")
    @Size(min = 7, max = 50,message = "Please use 7 to 50 digits")
    @Column(unique = true, length = 50)
    private String phone;
}
