package ch.samt.customers.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_seq")
    @SequenceGenerator(name = "reservation_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotBlank
    @Size(min = 1, max = 3, message = "Numero camera deve essere tra 1 e 999")
    private String room;

    @NotBlank
    @Size(min = 8, max = 16, message = "Ora del checkin tra 8 e 15 caratteri")
    private String checkin;

    @Size(min = 8, max = 16, message = "Ora del checkout tra 8 e 16 caratteri")
    private String checkout;
}