package euteam.cleanmachine.model.facility.machine.state;

import euteam.cleanmachine.model.facility.Machine;
import static euteam.cleanmachine.model.enums.State.OUTOFORDER;

import euteam.cleanmachine.exceptions.StateTransitionException;

import javax.persistence.Entity;

@Entity
public class OutOfOrderState extends MachineState {
    private Long employeId;

    public OutOfOrderState() {
    }


    public OutOfOrderState(Long employeId) {
        this.employeId = employeId;
        this.setName(OUTOFORDER.getName());
    }
    @Override
    public void idle(Machine m) {
        m.setState(new IdleState());
    }

    @Override
    public void startMachine(Machine machine, Long userId, Long EndTime, long programId) {
        throw new StateTransitionException("Cannot start machine if out of order");
    }

    @Override
    public void lockMachine(Machine machine, Long userId) {
        throw new StateTransitionException("Cannot lock machine if out if order");
    }

    @Override
    public void authenticateOnMachine(Machine machine, Long userId) {
        throw new StateTransitionException("Cannot authenticate on machine while out of order");
    }

    @Override
    public void outOfOrder(Machine machine, Long employeId) {
        throw new StateTransitionException("Machine already out of order");
    }

}
