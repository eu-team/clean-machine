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

    public MaintenanceReservation(Employee employee, Machine machine, Date startDate, Date endDate) {
        super(machine);
        this.startDate = startDate;
        this.endDate = endDate;
        this.employee = employee;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
