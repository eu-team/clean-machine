package euteam.cleanmachine.model.facility.machine;

import euteam.cleanmachine.model.user.User;

import javax.persistence.Entity;

@Entity
public class AuthenticatedState extends MachineState {
    private User user;
    AuthenticatedState(Machine M, User user) {
        super(M);
        this.user = user;
    }

    @Override
    public void authenticate(User user) {
        //already in this state
    }

    @Override
    public void startMachine() {
        m.setCurrentState(new RunningState(m,user));
    }

    @Override
    public void stopMachine() {
        m.setCurrentState(new NotInUseState(m));
    }
}
