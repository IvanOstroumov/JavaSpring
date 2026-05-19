package ch.samt.cirk.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actor_seq")
    @SequenceGenerator(name = "actor_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 30, message = "Lunghezza consentita tra 3 e 30 caratteri")
    private String name;

    @NotBlank
    @Size(min = 3, max = 30, message = "Lunghezza consentita tra 3 e 30 caratteri")
    private String surname;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;
}
