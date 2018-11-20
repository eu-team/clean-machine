package euteam.cleanmachine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import euteam.cleanmachine.model.reservation.OneTimeReservation;


import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OneTimeReservationDto {
    private Long id;
    private MachineDto machineDto;
    private Date reservationMadeDate;
    private Date startDate;
    private Date endDate;
    private UserDto userDto;

    public OneTimeReservationDto(OneTimeReservation oneTimeReservation) {
        this.id = oneTimeReservation.getId();
        this.machineDto = new MachineDto(oneTimeReservation.getMachine());
        this.reservationMadeDate = oneTimeReservation.getReservationMadeDate();
        this.startDate = oneTimeReservation.getStartDate();
        this.endDate = oneTimeReservation.getEndDate();
        this.userDto = new UserDto(oneTimeReservation.getUser());
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

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
