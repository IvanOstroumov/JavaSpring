package ch.samt.cirk.data;

import ch.samt.cirk.domain.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
    
}
