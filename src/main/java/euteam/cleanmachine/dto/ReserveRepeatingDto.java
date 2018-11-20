package euteam.cleanmachine.dto;

import euteam.cleanmachine.model.enums.ReservationPeriodicity;

import java.util.Date;

public class ReserveRepeatingDto {
    private Long machineId;
    private ReservationPeriodicity reservationPeriodicity;
    private Date startDate;
    private Date endDate;

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
