package euteam.cleanmachine.dto;

import java.util.Date;

public class ReserveOneTimeDto {
    private Date reservationDate;
    private Long machineId;

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }
}
