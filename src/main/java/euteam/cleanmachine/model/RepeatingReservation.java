package euteam.cleanmachine.model;

import euteam.cleanmachine.model.enums.ReservationPeriodicity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class RepeatingReservation extends Reservation{
    @ManyToOne
    private User user;

    private ReservationPeriodicity reservationPeriodicity;
}
