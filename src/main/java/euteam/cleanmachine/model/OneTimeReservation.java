package euteam.cleanmachine.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class OneTimeReservation extends Reservation {
    @ManyToOne
    private Customer customer;
}
