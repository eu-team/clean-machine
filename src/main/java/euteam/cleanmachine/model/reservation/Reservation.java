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

    public Reservation(Machine machine) {
        this.machine = machine;
        this.reservationMadeDate = new Date();
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
}
