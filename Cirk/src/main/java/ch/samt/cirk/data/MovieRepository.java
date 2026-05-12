package ch.samt.cirk.data;

import ch.samt.cirk.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    
}
