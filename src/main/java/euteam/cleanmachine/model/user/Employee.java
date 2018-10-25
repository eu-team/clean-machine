package euteam.cleanmachine.model.user;

import euteam.cleanmachine.model.facility.Facility;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public abstract class Employee extends User {
    @ManyToOne
    private Facility facility;
}
