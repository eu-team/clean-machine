package euteam.cleanmachine.model.facility;

import euteam.cleanmachine.exceptions.StateTransitionException;

import javax.persistence.Entity;

@Entity
public class IdleState extends MachineState{
    private static final String NAME = "Idle";

    @Override
    public String getStateName() {
        return NAME;
    }

    public IdleState() {
    }

    @Override
    public void idle(Machine m) {
        throw new StateTransitionException("Machine is already in idle state");
    }

    @Override
    public void startMachine(Machine machine, Long userId, Long EndTime, long programId) {
        throw new StateTransitionException("A user need to authenticate before starting a program");
    }

    @Override
    public void lockMachine(Machine machine, Long userId) {
        throw new StateTransitionException("Cannot Lock a machine from idle State (Can maybe change later on)");
    }

    @Override
    public void authenticateOnMachine(Machine machine, Long userId) {
        machine.setState(new AuthenticatedState(userId));
    }

    @Override
    public void outOfOrder(Machine machine, Long employeId) {
        machine.setState(new OutOfOrderState(employeId));
    }

}
