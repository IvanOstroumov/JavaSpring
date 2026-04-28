package ch.samt.customers.data;

import ch.samt.customers.domain.Customer;
import ch.samt.customers.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
