package euteam.cleanmachine.model.facility.machine.state;

import euteam.cleanmachine.model.facility.Machine;
import static euteam.cleanmachine.model.enums.State.OUTOFORDER;

import javax.persistence.Entity;

@Entity
public class OutOfOrderState extends MachineState {
    private Long employeId;

    public OutOfOrderState(Long employeId) {
        this.employeId = employeId;
        this.setName(OUTOFORDER.getName());
    }

    @Override
    public void idle(Machine m) {
        //not possible from this state
    }

    @Override
    public void startMachine(Machine machine, Long userId) {
        //not possible from this state
    }

    @Override
    public void authenticateOnMachine(Machine machine, Long userId) {
        //not possible from this state
    }

    @Override
    public void outOfOrder(Machine machine, Long employeId) {
        //not possible from this state
    }

    @Override
    public void reOpenMachine(Machine machine) {
        machine.setState(new IdleState());
    }
}
