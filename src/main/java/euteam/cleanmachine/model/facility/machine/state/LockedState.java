package euteam.cleanmachine.model.facility.machine.state;

import euteam.cleanmachine.exceptions.StateTransitionException;
import euteam.cleanmachine.model.facility.Machine;

import javax.persistence.Entity;
import java.util.concurrent.locks.Lock;

import static euteam.cleanmachine.model.enums.State.LOCKED;

@Entity
public class LockedState extends MachineState {
    private Long userId;

    public LockedState(){}
    public LockedState(Long userId) {
        this.userId = userId;
        setName(LOCKED.getName());
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

    @Override
    public void unlockMachine(Machine machine, Long employeID) {
        if(!employeID.equals(userId))throw new StateTransitionException("cannot unlock because given id's doesn't match current logged in user");
        machine.setState(new IdleState());
    }


    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
