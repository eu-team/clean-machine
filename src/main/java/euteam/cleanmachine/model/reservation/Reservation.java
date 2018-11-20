package euteam.cleanmachine.model.reservation;

import euteam.cleanmachine.model.facility.Machine;

import javax.persistence.*;
import java.util.Date;

@Entity
public abstract class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Machine machine;
    private Date reservationMadeDate;
    private Date startDate;
    private Date endDate;
    private boolean cancelled;

    public Reservation(Machine machine, Date startDate, Date endDate) {
        this.machine = machine;
        this.reservationMadeDate = new Date();
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancelled = false;
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

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
