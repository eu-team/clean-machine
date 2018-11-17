package euteam.cleanmachine.model.facility;

import euteam.cleanmachine.exceptions.StateTransitionException;

import javax.persistence.Entity;

@Entity
public class OutOfOrderState extends  MachineState {
    private Long employeId;

    public OutOfOrderState() {
    }

    private static final String NAME = "Out_Of_Order";

    public OutOfOrderState(Long employeId) {
        this.employeId = employeId;
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
