package euteam.cleanmachine.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public abstract class Employee extends User {
    @ManyToOne
    private Facility facility;
}
