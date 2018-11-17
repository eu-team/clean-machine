package euteam.cleanmachine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.reservation.OneTimeReservation;
import euteam.cleanmachine.model.user.Customer;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OneTimeReservationDto {
    private Long id;
    private Machine machine;
    private Date reservationMadeDate;
    private Date reservationDate;
    private Customer customer;

    public OneTimeReservationDto(OneTimeReservation oneTimeReservation) {
        this.id = oneTimeReservation.getId();
        this.machine = oneTimeReservation.getMachine();
        this.reservationMadeDate = oneTimeReservation.getReservationMadeDate();
        this.reservationDate = oneTimeReservation.getReservationDate();
        this.customer = oneTimeReservation.getCustomer();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
