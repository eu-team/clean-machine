package euteam.cleanmachine.model.facility.machine.state;

import euteam.cleanmachine.model.facility.*;

import javax.persistence.Entity;

import static euteam.cleanmachine.model.enums.State.AUTHENTICATED;

@Entity
public class AuthenticatedState extends MachineState {
    private Long userId;

    AuthenticatedState(Long userId){
        this.userId= userId;
        this.setName(AUTHENTICATED.getName());
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
        //Already authenticated
    }

    @Override
    public void outOfOrder(Machine machine, Long employeId) {
        machine.setState(new OutOfOrderState(employeId));
    }

    @Override
    public void reOpenMachine(Machine machine) {
        //cannot reopen while in this state
    }
}
