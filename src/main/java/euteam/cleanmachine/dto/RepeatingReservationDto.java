package euteam.cleanmachine.dto;

import euteam.cleanmachine.model.enums.ReservationPeriodicity;
import euteam.cleanmachine.model.reservation.RepeatingReservation;

import java.util.Date;

public class RepeatingReservationDto {
    private UserDto userDto;
    private ReservationPeriodicity reservationPeriodicity;
    private Date reservationMadeDate;
    private Date reservationDate;
    private MachineDto machineDto;

    public RepeatingReservationDto(RepeatingReservation repeatingReservation) {
        this.userDto = new UserDto(repeatingReservation.getUser());
        this.reservationPeriodicity = repeatingReservation.getReservationPeriodicity();
        this.reservationMadeDate = repeatingReservation.getReservationMadeDate();
        this.reservationDate =  repeatingReservation.getReservationDate();
        this.machineDto = new MachineDto(repeatingReservation.getMachine());
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

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public MachineDto getMachineDto() {
        return machineDto;
    }

    public void setMachineDto(MachineDto machineDto) {
        this.machineDto = machineDto;
    }
}
