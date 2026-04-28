package ch.samt.tournament.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_seq")
    @SequenceGenerator(name = "player_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 30, message = "Lunghezza consentita tra 3 e 50 caratteri")
    private String name;

    @NotEmpty
    @Max(100)
    @Min(1)
    private int age;

    @NotBlank
    @Size(min = 3, max = 30, message = "Lunghezza consentita tra 3 e 30 caratteri")
    private String gender;

    @NotBlank
    @Size(min = 3, max = 30, message = "Lunghezza consentita tra 3 e 30 caratteri")
    private String race;

    @NotBlank
    @Size(min = 3, max = 30, message = "Lunghezza consentita tra 3 e 30 caratteri")
    private String city;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    @Valid
    private Team team;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gameprofile_id")
    @Valid
    private GameProfile gameProfile;
}
