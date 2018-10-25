package euteam.cleanmachine.model.reservation;

import euteam.cleanmachine.model.enums.ReservationPeriodicity;
import euteam.cleanmachine.model.user.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class RepeatingReservation extends Reservation {
    @ManyToOne
    private User user;

    private ReservationPeriodicity reservationPeriodicity;
}
