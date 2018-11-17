package euteam.cleanmachine.model.facility;

import euteam.cleanmachine.model.facility.machine.state.*;

import javax.persistence.*;
import java.util.List;

@Entity
public abstract class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade=CascadeType.ALL)
    private MachineState state;

    private String identifier;

    @OneToMany
    private List<Program> programs;

    Machine(){
        setState(new IdleState());
    }

    Machine(String identifier) {
        this.identifier = identifier;
        setState(new IdleState());
    }

    public void setState(MachineState state){
        checkIfRunningStateIsOver(state);
        this.state = state;
    }

    public void checkIfRunningStateIsOver(MachineState state) {
        if(state == null) state = this.state;
        if (!(state instanceof RunningState)) {
            return;
        }
        RunningState runningState = (RunningState)state;
        if(runningState.getEndTime()<= System.currentTimeMillis()){
            lockMachine(runningState.getUserId());
            return;
        }
    }

    public void idle(){
        state.idle(this);
    }
    public void authenticateOnMachine(Long userId){
        state.authenticateOnMachine(this,userId);
    }
    public void startMachine(Long userId, long programId){
        Program p  =  getProgramById(programId);
        long durationInMiliseconds =Math.round(p.getDuration() * 60 * 1000);
        state.startMachine(this,userId,durationInMiliseconds + System.currentTimeMillis(),programId );
    }
    private void lockMachine(Long userId){
        state.lockMachine(this,userId);
    }
    public boolean unlockMachine(long userId){
        Long loggedInUserID = null;
        if(state instanceof LockedState){
            LockedState state = (LockedState) getState();
          loggedInUserID=  state.getUserId();
        }else if(state instanceof RunningState){
            RunningState state = (RunningState) getState();
            loggedInUserID=  state.getUserId();
        }
        if(loggedInUserID == userId){
            idle();
            return true;
        }
        return false;
    }
    public long getProgramEndTime() throws ClassCastException{
        RunningState runningState = (RunningState) state;
         return runningState.getEndTime();
    }
    public void outOfOrder(Long employeId){
        state.outOfOrder(this,employeId);
    }


    public Long getLoggedInUserId(){
        try {
            AuthenticatedState authenticatedState = (AuthenticatedState) state;
            return authenticatedState.getUserId();
        }catch (Exception e){
            return null;
        }

    }
    public MachineState getState() {
        return state;
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

    public boolean containsProgram(Long programId){
        for (Program program:programs
             ) {
            if(program.getId()==programId)return true;
        }
        return false;
    }
    public Program getProgramById(long id){
        Program result = null;
        for (Program program:programs) {
            if(program.getId()==id)result = program;
        }
        return result;
    }


}
