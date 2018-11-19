package euteam.cleanmachine.model.facility.machine.state;

import euteam.cleanmachine.model.facility.Machine;

import static euteam.cleanmachine.model.enums.State.RUNNING;

import javax.persistence.Entity;

@Entity
public class RunningState extends MachineState {
    private Long userId;
    public RunningState(Long userId) {
        this.userId = userId;
        this.setName(RUNNING.getName());
    }

    @Override
    public void idle(Machine m) {
        m.setState(new IdleState());
    }

    @Override
    public void startMachine(Machine machine, Long userId) {
        //already running
    }

    @Override
    public void authenticateOnMachine(Machine machine, Long userId) {
        //Not possible
    }

    @Override
    public void outOfOrder(Machine machine, Long employeId) {
        machine.setState(new OutOfOrderState(employeId));
    }

    @Override
    public void reOpenMachine(Machine machine) {
        //Not possible
    }
}
