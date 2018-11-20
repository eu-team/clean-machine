package euteam.cleanmachine.model.reservation;

import euteam.cleanmachine.model.enums.ReservationPeriodicity;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.user.User;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class RepeatingReservation extends Reservation {
    private ReservationPeriodicity reservationPeriodicity;

    public RepeatingReservation(Machine machine, User user, ReservationPeriodicity reservationPeriodicity, Date startDate, Date endDate) {
        super(machine, user, startDate, endDate);
        this.reservationPeriodicity = reservationPeriodicity;
    }

    public ReservationPeriodicity getReservationPeriodicity() {
        return reservationPeriodicity;
    }

    public void setReservationPeriodicity(ReservationPeriodicity reservationPeriodicity) {
        this.reservationPeriodicity = reservationPeriodicity;
    }
}
