package euteam.cleanmachine.dto;

import euteam.cleanmachine.model.enums.ReservationPeriodicity;
import euteam.cleanmachine.model.reservation.RepeatingReservation;

import java.util.Date;

public class RepeatingReservationDto {
    private Long id;
    private UserDto userDto;
    private ReservationPeriodicity reservationPeriodicity;
    private Date reservationMadeDate;
    private Date startDate;
    private Date endDate;
    private MachineDto machineDto;

    public RepeatingReservationDto(RepeatingReservation repeatingReservation) {
        this.id = repeatingReservation.getId();
        this.userDto = new UserDto(repeatingReservation.getUser());
        this.reservationPeriodicity = repeatingReservation.getReservationPeriodicity();
        this.reservationMadeDate = repeatingReservation.getReservationMadeDate();
        this.startDate =  repeatingReservation.getStartDate();
        this.endDate = repeatingReservation.getEndDate();
        this.machineDto = new MachineDto(repeatingReservation.getMachine());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public ReservationPeriodicity getReservationPeriodicity() {
        return reservationPeriodicity;
    }

    public void setReservationPeriodicity(ReservationPeriodicity reservationPeriodicity) {
        this.reservationPeriodicity = reservationPeriodicity;
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

    public MachineDto getMachineDto() {
        return machineDto;
    }

    public void setMachineDto(MachineDto machineDto) {
        this.machineDto = machineDto;
    }
}
