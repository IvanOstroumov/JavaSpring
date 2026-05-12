package ch.samt.cirk.data;


import ch.samt.cirk.domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {

}
