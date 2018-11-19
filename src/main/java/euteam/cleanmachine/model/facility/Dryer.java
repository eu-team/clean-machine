package euteam.cleanmachine.model.facility;

import javax.persistence.Entity;

@Entity
public class Dryer extends Machine {

    public Dryer (String identifier) {
        super(identifier);
    }

    public Dryer() {
        super();
    }

}
