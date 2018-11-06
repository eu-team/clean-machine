package euteam.cleanmachine.model.facility.machine;

import euteam.cleanmachine.model.user.User;

import javax.persistence.Entity;

@Entity
public class NotInUseState extends MachineState {


    NotInUseState(Machine M) {
        super(M);
    }

    @Override
    public void authenticate(User user) {
        m.setCurrentState(new AuthenticatedState(m,user));
    }

    @Override
    public void startMachine() {
        //Cannot start without authentication
    }

    @Override
    public void stopMachine() {
        //already in stop state
    }
}
