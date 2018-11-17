package euteam.cleanmachine.dto;

import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.reservation.MaintenanceReservation;
import euteam.cleanmachine.model.user.Employee;

import java.util.Date;

public class MaintenanceReservationDto {
    private Machine machine;
    private Date reservationMadeDate;
    private Date startDate;
    private Date endDate;
    private Employee employee;

    public MaintenanceReservationDto(MaintenanceReservation maintenanceReservation) {
        this.machine = maintenanceReservation.getMachine();
        this.employee = maintenanceReservation.getEmployee();
        this.startDate = maintenanceReservation.getStartDate();
        this.endDate = maintenanceReservation.getEndDate();
        this.reservationMadeDate = maintenanceReservation.getReservationMadeDate();
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Date getReservationMadeDate() {
        return reservationMadeDate;
    }

    public void setReservationMadeDate(Date reservationMadeDate) {
        this.reservationMadeDate = reservationMadeDate;
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

