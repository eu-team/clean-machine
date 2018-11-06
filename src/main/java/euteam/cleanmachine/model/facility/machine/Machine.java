package euteam.cleanmachine.model.facility.machine;

import euteam.cleanmachine.model.facility.Program;
import euteam.cleanmachine.model.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Program> programs;



    private MachineState currentState;

    public Machine(){
        programs = new ArrayList<>();
        currentState = new NotInUseState(this);
    }

    public MachineState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(MachineState currentState) {
        this.currentState = currentState;
    }
    public void authenticate(User u ){
        currentState.authenticate(u);
    }
    public void startMachine(){
        currentState.startMachine();
    }
    public void stopMachine(){
        currentState.stopMachine();
    }

    public List<Program> getPrograms(){
        return  programs;
    }
    public void addProgram(Program program){
        programs.add(program);
    }
}
