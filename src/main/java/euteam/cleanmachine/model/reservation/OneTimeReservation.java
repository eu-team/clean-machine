package euteam.cleanmachine.model.reservation;

import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.user.Customer;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class OneTimeReservation extends Reservation {

    public OneTimeReservation(Customer customer, Date startDate, Date endDate, Machine machine) {
        super(machine, customer, startDate, endDate);
    }
}
