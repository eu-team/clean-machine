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
    @ManyToOne (fetch = FetchType.EAGER)
    private User user;
    private ReservationPeriodicity reservationPeriodicity;

    public RepeatingReservation(Machine machine, User user, ReservationPeriodicity reservationPeriodicity, Date startDate, Date endDate) {
        super(machine, startDate, endDate);
        this.user = user;
        this.reservationPeriodicity = reservationPeriodicity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ReservationPeriodicity getReservationPeriodicity() {
        return reservationPeriodicity;
    }

    public void setReservationPeriodicity(ReservationPeriodicity reservationPeriodicity) {
        this.reservationPeriodicity = reservationPeriodicity;
    }
}
