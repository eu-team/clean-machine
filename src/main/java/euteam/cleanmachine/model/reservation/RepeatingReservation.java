package euteam.cleanmachine.model.reservation;

import euteam.cleanmachine.model.enums.ReservationPeriodicity;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.user.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class RepeatingReservation extends Reservation {
    @ManyToOne
    private User user;
    private ReservationPeriodicity reservationPeriodicity;
    private Date reservationDate;

    public RepeatingReservation(Machine machine, User user, ReservationPeriodicity reservationPeriodicity, Date reservationDate) {
        super(machine);
        this.user = user;
        this.reservationPeriodicity = reservationPeriodicity;
        this.reservationDate  = reservationDate;
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

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
}
