package euteam.cleanmachine.model.facility.machine.state;

import euteam.cleanmachine.model.facility.Machine;

import javax.persistence.Entity;

import static euteam.cleanmachine.model.enums.State.RESERVED;

@Entity
public class ReservedState extends MachineState {

    private Long userId;

    public ReservedState() {}

    public ReservedState(Long userId){
        this.userId= userId;
        this.setName(RESERVED.getName());
    }

    @Override
    public void idle(Machine m) {
        m.setState(new IdleState());
    }

    @Override
    public void startMachine(Machine machine, Long userId) {
        machine.setState(new RunningState(userId));
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
    public void reOpenMachine(Machine machine) {
        machine.setState(new IdleState());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
