package ch.samt.cirk.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "director_seq")
    @SequenceGenerator(name = "director_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 30, message = "Lunghezza consentita tra 3 e 30 caratteri")
    private String name;

    @NotBlank
    @Size(min = 3, max = 30, message = "Lunghezza consentita tra 3 e 30 caratteri")
    private String surname;

    @ToString.Exclude
    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL)
    private List<Movie> movies;
}
