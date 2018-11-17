package euteam.cleanmachine.model.facility;

import euteam.cleanmachine.exceptions.StateTransitionException;

import javax.persistence.Entity;

@Entity
public class AuthenticatedState extends MachineState {

    private Long userId;
    private static final String NAME = "Logged_In";

    AuthenticatedState(Long userId){
        this.userId= userId;
    }

    @Override
    public String getStateName() {
        return NAME;
    }

    public AuthenticatedState() {
    }

    @Override
    public void idle(Machine m) {
        m.setState(new IdleState());
    }

    @Override
    public void startMachine(Machine machine, Long userId, Long EndTime, long programId) {
        machine.setState(new RunningState(userId,EndTime,programId));
    }

    @Override
    public void lockMachine(Machine machine, Long userId) {
        throw new StateTransitionException("Cannot lock machine while authenticated");
    }

    @Override
    public void authenticateOnMachine(Machine machine, Long userId) {
        throw new StateTransitionException("Already authenticate on machine");
    }

    @Override
    public void outOfOrder(Machine machine, Long employeId) {
        machine.setState(new OutOfOrderState(employeId));
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
