package euteam.cleanmachine.model.facility.machine.state;

import euteam.cleanmachine.model.facility.Machine;

import static euteam.cleanmachine.model.enums.State.RUNNING;

import euteam.cleanmachine.exceptions.StateTransitionException;

import javax.persistence.Entity;

@Entity
public class RunningState extends MachineState {
    private Long userId;
    private Long endTime;
    private long programId;
    private static final String NAME = "Running";



    public RunningState(){

    }
    public RunningState(Long userId, Long endTime, long programId) {
        this.userId = userId;
        this.endTime = endTime;
        this.programId = programId;
        setName(RUNNING.getName());
    }


    @Override
    public void idle(Machine m) {
        m.setState(new IdleState());
    }

    @Override
    public void startMachine(Machine machine, Long userId, Long EndTime, long programId) {
        throw new StateTransitionException("Machine already started");
    }

    @Override
    public void lockMachine(Machine machine, Long userId) {
        machine.setState(new LockedState(userId));
    }

    @Override
    public void authenticateOnMachine(Machine machine, Long userId) {
        throw new StateTransitionException("Cannot authenticate while the machine is running");
    }

    @Override
    public void outOfOrder(Machine machine, Long employeId) {
        machine.setState(new OutOfOrderState(employeId));
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
