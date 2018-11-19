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
    private String name;

    public abstract void idle(Machine m);
    public abstract void startMachine(Machine machine,Long userId);
    public abstract void authenticateOnMachine(Machine machine,Long userId);
    public abstract void outOfOrder(Machine machine,Long employeId);
    public abstract void reOpenMachine(Machine machine);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
