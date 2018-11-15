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

    private String identifier;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public boolean containsProgram(Long programId){
        for (Program program:programs
             ) {
            if(program.getId()==programId)return true;
        }
        return false;
    }
}
