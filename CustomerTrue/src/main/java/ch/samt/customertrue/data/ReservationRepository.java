package ch.samt.customertrue.data;

import ch.samt.customertrue.domain.Customer;
import ch.samt.customertrue.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
