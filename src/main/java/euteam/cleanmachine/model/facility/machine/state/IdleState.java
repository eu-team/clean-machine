package euteam.cleanmachine.model.facility.machine.state;

import euteam.cleanmachine.model.facility.Machine;

import javax.persistence.Entity;

import static euteam.cleanmachine.model.enums.State.IDLE;

@Entity
public class IdleState extends MachineState {

    public IdleState() {
        this.setName(IDLE.getName());
    }

    @Override
    public void idle(Machine m) {
        //already in idle state
    }

    @Override
    public void startMachine(Machine machine, Long userId) {
        //need to authenticate first
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
}
