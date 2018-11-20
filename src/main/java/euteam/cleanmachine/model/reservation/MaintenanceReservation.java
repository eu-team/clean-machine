package euteam.cleanmachine.model.reservation;

import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.user.Employee;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class MaintenanceReservation extends Reservation {
    public MaintenanceReservation(Employee employee, Machine machine, Date startDate, Date endDate) {
        super(machine, employee, startDate, endDate);
    }
}
