package euteam.cleanmachine.model;

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
