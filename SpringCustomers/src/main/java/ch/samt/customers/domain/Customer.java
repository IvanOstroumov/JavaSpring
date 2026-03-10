package ch.samt.customers.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    private Long id;

    @NotBlank
    @Size(min = 2, message = "Lunghezza tra 2 e 10 caratteri")
    private String name;

    @NotBlank
    @Size(min = 2, message = "Lunghezza tra 2 e 10 caratteri")
    private String surname;

    @NotNull
    @Min(18)
    @Max(99)
    private Integer age;

    @NotBlank
    @Size(min = 3, max = 20)
    private String city;

    @NotNull
    private long ccnumber;

    @NotNull
    @Pattern(regexp = "^(0[1-9]|1[0-2])\\/([0-9]{2})$", message = "Il formato della data deve essere MM/YY")
    private String ccexpiration;

    @NotNull
    @Digits(integer = 3, fraction = 0)
    private Integer cccvv;
}
