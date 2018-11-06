package euteam.cleanmachine.model.facility.machine;

import euteam.cleanmachine.model.user.User;

import javax.persistence.Entity;

@Entity
public class RunningState extends MachineState {
    private User user;
    RunningState(Machine M, User user) {
        super(M);
        this.user = user;
    }

    @Override
    public void authenticate(User user) {
        //Can't go in authentication state
    }

    @Override
    public void startMachine() {
            //already in running state
    }

    @Override
    public void stopMachine() {
        m.setCurrentState(new NotInUseState(m));
    }
}
