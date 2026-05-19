package ch.samt.customertrue.service;

import ch.samt.customertrue.data.CustomerRepository;
import ch.samt.customertrue.data.ReservationRepository;
import ch.samt.customertrue.domain.Customer;
import ch.samt.customertrue.domain.Reservation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public void save(@Valid Reservation reservation) {
        try {
            reservationRepository.save(reservation);
        } catch (RuntimeException ex) {
            log.error("Error saving reservation: " + reservation.getId());
            throw new RuntimeException("Errore durante il save del reservation in DB: " + reservation, ex);
        }
        log.info("Reservation " + reservation.getId() + " saved");
    }
}