package euteam.cleanmachine.model.facility.machine.state;

import euteam.cleanmachine.model.facility.Machine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class MachineState {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public abstract void idle(Machine m);
    public abstract void startMachine(Machine machine,Long userId);
    public abstract void authenticateOnMachine(Machine machine,Long userId);
    public abstract void outOfOrder(Machine machine,Long employeId);
    public abstract void reOpenMachine(Machine machine);

}
