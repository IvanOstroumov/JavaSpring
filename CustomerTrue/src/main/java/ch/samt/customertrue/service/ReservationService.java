package ch.samt.customertrue.service;

import ch.samt.customertrue.data.CustomerRepository;
import ch.samt.customertrue.data.ReservationRepository;
import ch.samt.customertrue.domain.Customer;
import ch.samt.customertrue.domain.Reservation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new RuntimeException("Errore durante il save del reservation in DB: " + reservation, ex);
        }
    }
}