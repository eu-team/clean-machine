package euteam.cleanmachine.model.reservation;

import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.user.Customer;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class OneTimeReservation extends Reservation {
    @ManyToOne
    private Customer customer;
    private Date reservationDate;

    public OneTimeReservation(Customer customer, Date reservationDate, Machine machine) {
        super(machine);
        this.customer = customer;
        this.reservationDate = reservationDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
}
