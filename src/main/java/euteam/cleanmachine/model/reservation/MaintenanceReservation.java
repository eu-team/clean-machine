package euteam.cleanmachine.model.reservation;

import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.user.Employee;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class MaintenanceReservation extends Reservation {
    private Date startDate;

    private Date endDate;

    @ManyToOne
    private Employee employee;

    public MaintenanceReservation(Machine machine) {
        super(machine);
    }
}
