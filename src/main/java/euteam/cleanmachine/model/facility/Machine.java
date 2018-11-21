package euteam.cleanmachine.model.facility;

import euteam.cleanmachine.exceptions.StateTransitionException;
import euteam.cleanmachine.model.facility.machine.state.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public abstract class Machine {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String identifier;

    @OneToOne(cascade=CascadeType.ALL)
    private MachineState state;

    @OneToMany
    private List<Program> programs;

    Machine(){
        setState(new IdleState());
    }

    public void setState(MachineState state){
        checkIfRunningStateIsOver(state);
        this.state = state;
    }

    /**
     * Checks if the given state or if null the current state is done running.
     * method will end when not in running state
     * if done running it will change the state to locked
     * @param state
     */
    public void checkIfRunningStateIsOver(MachineState state) {
        if(state == null) {
            state = this.state;
        } else {
            this.state = state;
        }
        if (!(state instanceof RunningState)) {
            return;
        }
        RunningState runningState = (RunningState)state;
        if(runningState.getEndTime()<= System.currentTimeMillis()){
            lockMachine(runningState.getUserId());
            return;
        }
    }

    /**
     * Sets the machine state to idle If the current state allows its
     * @return ifChanged
     */
    public boolean idle(){
        try {
            state.idle(this);
        }catch (Exception e){
            return  false ;
        }
        return true;
    }
    /**
     * sets the machine state to authenticated if the current states allow it
     * @throws StateTransitionException
     */
    public void authenticateOnMachine(Long userId){
        state.authenticateOnMachine(this,userId);
    }
    /*
      * Sets the machine state to running If the current state allows its
     * @param userId user thats starts the machine
     * @param programId program that user selected
     * @return ifChanged
     */
    public boolean startMachine(Long userId, long programId){
        Program p  =  getProgramById(programId);
        long durationInMiliseconds =Math.round(p.getDuration() * 60 * 1000);
        try {
            state.startMachine(this, userId, durationInMiliseconds + System.currentTimeMillis(), programId);
        }catch (StateTransitionException e){
            return false;
        }
        return true;
    }

    /**
     * sets the machine state to locked if the current state allows it
     * @param userId
     */
    private void lockMachine(Long userId){
        state.lockMachine(this,userId);
    }

    /**
     * sets the machine state to idle if the current states allows it
     * @param userId user that unlocks it
     * @return ifchanged
     */
    public boolean unlockMachine(long userId){
        try{
            state.unlockMachine(this,userId);
        }catch(StateTransitionException e){
            throw e;
        }
        return true;
    }

    /**
     * Only works if machine is currently running a program
     * @return Long program end time
     */
    public Long getProgramEndTime(){
        if( state instanceof  RunningState) {
            RunningState runningState = (RunningState) state;
            return runningState.getEndTime();
        }
        return null;
    }

    /**
     * sets machine to out of order
     * @throws StateTransitionException if current state doesn"t allow it
     */
    public void outOfOrder(Long employeId){
        state.outOfOrder(this,employeId);
    }


    /**
     * get the user that is currently authenticated on the machine
     * @return Long userID of currently logged in user
     */
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

    /**
     * Checks if machine contains the given program
     * @param programId
     * @return boolean
     */
    public boolean containsProgram(Long programId){
        for (Program program:programs
             ) {
            if(program.getId().equals(programId)) return true;
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

    public void addProgram(Program program) {
        this.programs.add(program);
    }
}
