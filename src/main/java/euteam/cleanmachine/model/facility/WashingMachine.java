package euteam.cleanmachine.model.facility;


import javax.persistence.Entity;

@Entity
public class WashingMachine extends Machine {

    public WashingMachine(String identifier) {
        super(identifier);
    }

    public WashingMachine() {
        super();
    }
}
