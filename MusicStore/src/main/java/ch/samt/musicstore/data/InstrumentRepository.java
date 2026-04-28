package ch.samt.musicstore.data;

import ch.samt.musicstore.domain.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    Instrument findById(long id);
    List<Instrument> findAll();
    List<Instrument> findByTypeIgnoreCase(String type);
    List<Instrument> findByBrandIgnoreCase(String brand);
    List<Instrument> findByPriceLessThanEqual(double price);
    List<Instrument> findByTypeIgnoreCaseAndBrandIgnoreCase(String type, String brand);

}
