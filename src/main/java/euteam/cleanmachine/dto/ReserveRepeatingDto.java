package euteam.cleanmachine.dto;

import euteam.cleanmachine.model.enums.ReservationPeriodicity;

import java.util.Date;

public class ReserveRepeatingDto {
    private Long machineId;
    private ReservationPeriodicity reservationPeriodicity;
    private Date reservationDate;

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public ReservationPeriodicity getReservationPeriodicity() {
        return reservationPeriodicity;
    }

    public void setReservationPeriodicity(ReservationPeriodicity reservationPeriodicity) {
        this.reservationPeriodicity = reservationPeriodicity;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
}
