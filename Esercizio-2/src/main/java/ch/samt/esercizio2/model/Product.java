package ch.samt.esercizio2.model;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Product {

    @NotEmpty(message = "Il campo id non può essere vuoto")
    @Positive(message = "L'id deve essere positivo")
    private int id;

    @NotBlank(message = "Il campo nome non può essere vuoto")
    @Size(min = 3, max = 100, message = "Il nome deve essere tra 3 e 100 caratteri")
    private String name;

    @NotEmpty(message = "Il campo price non può essere vuoto")
    @Positive
    private double price;

    @NotEmpty(message = "Il campo expirationDate non può essere vuoto")
    @Positive(message = "La data deve essere positiva")
    @FutureOrPresent(message = "La data deve essere nel fututo o presente")
    private LocalDate expirationDate;


}
