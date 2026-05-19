package ch.samt.cirk.data;

import ch.samt.cirk.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleIgnoreCase(String title);
}
