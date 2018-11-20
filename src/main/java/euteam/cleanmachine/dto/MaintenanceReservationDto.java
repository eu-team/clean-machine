package euteam.cleanmachine.dto;

import euteam.cleanmachine.model.reservation.MaintenanceReservation;

import java.util.Date;

public class MaintenanceReservationDto {
    private MachineDto machineDto;
    private Date reservationMadeDate;
    private Date startDate;
    private Date endDate;
    private UserDto userDto;

    public MaintenanceReservationDto(MaintenanceReservation maintenanceReservation) {
        this.machineDto = new MachineDto(maintenanceReservation.getMachine());
        this.userDto = new UserDto(maintenanceReservation.getUser());
        this.startDate = maintenanceReservation.getStartDate();
        this.endDate = maintenanceReservation.getEndDate();
        this.reservationMadeDate = maintenanceReservation.getReservationMadeDate();
    }

    public MachineDto getMachineDto() {
        return machineDto;
    }

    public void setMachineDto(MachineDto machineDto) {
        this.machineDto = machineDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
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
}

