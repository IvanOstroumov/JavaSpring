package ch.samt.tournament.data;

import ch.samt.tournament.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    List<Player> findByIsdeletedFalse();
    Player findByName(String name);
}
