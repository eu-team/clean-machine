package euteam.cleanmachine.model.facility.machine;

import euteam.cleanmachine.model.user.User;

import javax.persistence.Entity;

@Entity
public abstract class MachineState {
    protected Machine m;

    MachineState(Machine M){

    }

    public abstract void authenticate(User user);

    public abstract void startMachine();

    public abstract void stopMachine();


}
