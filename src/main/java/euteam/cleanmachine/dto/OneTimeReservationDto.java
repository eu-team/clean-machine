package euteam.cleanmachine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import euteam.cleanmachine.model.reservation.OneTimeReservation;


import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OneTimeReservationDto {
    private Long id;
    private MachineDto machineDto;
    private Date reservationMadeDate;
    private Date reservationDate;
    private UserDto userDto;

    public OneTimeReservationDto(OneTimeReservation oneTimeReservation) {
        this.id = oneTimeReservation.getId();
        this.machineDto = new MachineDto(oneTimeReservation.getMachine());
        this.reservationMadeDate = oneTimeReservation.getReservationMadeDate();
        this.reservationDate = oneTimeReservation.getReservationDate();
        this.userDto = new UserDto(oneTimeReservation.getCustomer());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MachineDto getMachineDto() {
        return machineDto;
    }

    public void setMachineDto(MachineDto machineDto) {
        this.machineDto = machineDto;
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

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
