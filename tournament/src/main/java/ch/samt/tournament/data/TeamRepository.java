package ch.samt.tournament.data;


import ch.samt.tournament.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    List<Team> findByIsdeletedFalse();
}
