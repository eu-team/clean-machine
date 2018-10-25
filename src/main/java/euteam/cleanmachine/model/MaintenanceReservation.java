package euteam.cleanmachine.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class MaintenanceReservation extends Reservation {
    private Date startDate;

    private Date endDate;

    @ManyToOne
    private Employee employee;
}
