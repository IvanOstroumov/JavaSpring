package ch.samt.cirk.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_seq")
    @SequenceGenerator(name = "movie_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 30, message = "Lunghezza consentita tra 3 e 30 caratteri")
    private String title;

    @NotBlank
    @Size(min = 3, max = 30, message = "Lunghezza consentita tra 3 e 30 caratteri")
    private String genre;

    @ManyToOne
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    @ManyToMany
    @JoinTable(name = "movie_actor", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
    @ToString.Exclude
    private List<Actor> actors;
}