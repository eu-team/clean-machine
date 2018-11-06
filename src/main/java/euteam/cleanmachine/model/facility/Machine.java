package euteam.cleanmachine.model.facility;

import javax.persistence.*;
import java.util.List;

@Entity
public abstract class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private MachineState state;

    @OneToMany
    private List<Program> programs;

    Machine(){
        setState(new IdleState());
    }

    public void setState(MachineState state){
        this.state = state;
    }

    public void Idle(){
        state.idle(this);
    }
    public void startMachine(Long userId){
        state.startMachine(this,userId);
    }
    public void authenticateOnMachine(Long userId){
        state.authenticateOnMachine(this,userId);
    }
    public void outOfOrder(Long employeId){
        state.outOfOrder(this,employeId);
    }
    public void reopenMachine(Long employeId){
        state.reOpenMachine(this);
    }
}
