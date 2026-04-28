package ch.samt.tournament.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class GameProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gameprofile_seq")
    @SequenceGenerator(name = "gameprofile_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 30, message = "Lunghezza consentita tra 3 e 30 caratteri")
    private String nickname;

}
