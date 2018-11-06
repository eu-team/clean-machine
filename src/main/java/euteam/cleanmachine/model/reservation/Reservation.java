package euteam.cleanmachine.model.reservation;

import euteam.cleanmachine.model.facility.machine.Machine;

import javax.persistence.*;
import java.util.Date;

@Entity
public abstract class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Machine machine;

    private Date reservationDate;
}
