package ch.samt.musicstore.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.NumberFormat;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Instrument {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instrument_seq")
    @SequenceGenerator(name = "instrument_seq", sequenceName = "instrument_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 4, max = 25)
    private String type;

    @NotBlank
    @Size(min = 2, max = 75)
    private String brand;

    @NotBlank
    @Size(min = 2, max = 100)
    private String model;

    @NotNull
    @Min(0)
    private double price;

    @NotNull
    @Min(0)
    private int stock;
}
