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

    /**
     * When a machine goes back to is idle state means aka machine is not being used
     * @param m
     */
    public abstract void idle(Machine m);

    /**
     * start a program on the machine with a given end time and userid
     * @param machine
     * @param userId
     * @param EndTime
     * @param programId
     */
    public abstract void startMachine(Machine machine, Long userId, Long EndTime, long programId);

    /**
     * locks the machine, ussualy done when the machine is finished with a program
     * @param machine
     * @param userId
     */
    public abstract void lockMachine(Machine machine, Long userId);

    /**
     * a user logs in on the machine
     * @param machine
     * @param userId
     */
    public abstract void authenticateOnMachine(Machine machine,Long userId);

    /**
     * a employe sets the machine out of order
     * @param machine
     * @param employeId
     */
    public abstract void outOfOrder(Machine machine,Long employeId);

    public abstract  void unlockMachine(Machine machine,Long employeID);

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
