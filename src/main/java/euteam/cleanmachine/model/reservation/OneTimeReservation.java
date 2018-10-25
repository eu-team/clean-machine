package euteam.cleanmachine.model.reservation;

import euteam.cleanmachine.model.user.Customer;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class OneTimeReservation extends Reservation {
    @ManyToOne
    private Customer customer;
}
