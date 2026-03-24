package ch.samt.gardenwarehouse.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    @SequenceGenerator(name = "item_seq", sequenceName = "item_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Pattern(regexp = "^[a-z]{3}-\\d{2}$", message = "Il codice è strano")
    private String code;

    @NotBlank
    private String type;

    @NotBlank
    @Size(min = 3, max = 25)
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private int itemcount;

}
