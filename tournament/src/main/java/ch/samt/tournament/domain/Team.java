package ch.samt.tournament.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_seq")
    @SequenceGenerator(name = "team_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50, message = "Lunghezza consentita tra 3 e 50 caratteri")
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Player> players;
}
