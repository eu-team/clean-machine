package euteam.cleanmachine.model.facility.machine.state;

import euteam.cleanmachine.model.enums.State.*;
import euteam.cleanmachine.model.facility.Machine;

import euteam.cleanmachine.exceptions.StateTransitionException;

import javax.persistence.Entity;

import static euteam.cleanmachine.model.enums.State.IDLE;

@Entity
public class IdleState extends MachineState{

    public IdleState() {
        setName(IDLE.getName());
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

    @Override
    public void unlockMachine(Machine machine, Long employeID) {
        throw new StateTransitionException("cannot unlock machine in this state");
    }

}
