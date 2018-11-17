package euteam.cleanmachine.model.facility;

import euteam.cleanmachine.exceptions.StateTransitionException;

import java.util.concurrent.locks.Lock;

public class LockedState extends MachineState {
    private Long userId;
    private static final String NAME = "Locked";

    public LockedState(){}
    public LockedState(Long userId) {
        this.userId = userId;
    }

    @Override
    public String getStateName() {
        return NAME;
    }

    @Override
    public void idle(Machine m) {
        m.setState(new IdleState());
    }

    @Override
    public void startMachine(Machine machine, Long userId, Long EndTime, long programId) {
        throw new StateTransitionException("Cannot start a machine while it's locked");
    }

    @Override
    public void lockMachine(Machine machine, Long userId) {
        throw new StateTransitionException("Machine already locked");
    }

    @Override
    public void authenticateOnMachine(Machine machine, Long userId) {
        throw new StateTransitionException("Cannot authenticate on machine while locked");
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
