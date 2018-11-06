package euteam.cleanmachine.model.facility.machine;

import euteam.cleanmachine.model.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class MachineState {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    protected Machine m;

    MachineState(Machine M){

    }

    public abstract void authenticate(User user);

    public abstract void startMachine();

    public abstract void stopMachine();


}
