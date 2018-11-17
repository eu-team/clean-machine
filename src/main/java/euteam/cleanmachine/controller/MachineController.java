package euteam.cleanmachine.controller;

import euteam.cleanmachine.dto.DtoFactory;
import euteam.cleanmachine.dto.ProgramDto;
import euteam.cleanmachine.exceptions.StateTransitionException;
import euteam.cleanmachine.model.billing.OneTimePayment;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.facility.MachineState;
import euteam.cleanmachine.model.facility.Program;
import euteam.cleanmachine.model.facility.RunningState;
import euteam.cleanmachine.model.user.User;
import euteam.cleanmachine.service.AccountService;
import euteam.cleanmachine.service.MachineService;
import euteam.cleanmachine.service.UserService;
import javafx.application.Preloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

@RestController
@CrossOrigin
public class MachineController {

    @Autowired
    private MachineService machineService;

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    /**
     * Retrieves the status from the machine
     * @return
     */
    @RequestMapping(path="/machine/getStatus", method = RequestMethod.GET)
    public ResponseEntity<?> getMachineStatus(){
        //GET Logged in machine
        Machine machine = getMachineByAuthentication();
        //get Status from the machine
        MachineState state= machine.getState();
        String stateName= state.getStateName();
        //Prepare result into object
        HashMap<String,String> result = new HashMap<>();
        result.put("status",stateName);

        return ResponseEntity.ok(result);
    }

    /**
     * A Customer or employe logs in on the machine
     * @param authItemToken (USER token that can be found on card)
     * @return JSON
     */
    @RequestMapping(path="/machine/login", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUserOnMachine(@RequestParam("authItemToken") Long authItemToken){
        Machine machine = getMachineByAuthentication();

            User u = userService.getUserByAuthId(authItemToken);
        if(u== null){
              return   ResponseEntity.status(400).body("No user found linked to this Authentication item");
        }
        try {
            machine.authenticateOnMachine(u.getId());
        }catch(StateTransitionException e){
            return ResponseEntity.status(400).body("Cannot change to authentication state at the moment");
        }
        machineService.update(machine);

        List<ProgramDto> programs = new ArrayList<>();
        for (Program program: machine.getPrograms()) {
            programs.add(DtoFactory.programToDto(program));
        }
        HashMap<String, Object> result = new HashMap<>();
        result.put("Programs",programs );
        result.put("Account Balance", accountService.getAccountByUser(u).getBalance());
        return ResponseEntity.ok(result);
    }

    /**
     * gives all programs and user balance, used to get to machine state after reboot;
     * @return
     */
    @RequestMapping(path="/machine/getLoggedInInfo", method = RequestMethod.GET)
    public ResponseEntity<?> getAuthenticateInfo(){
        Machine machine = getMachineByAuthentication();
        User u = userService.getUserByID(machine.getLoggedInUserId());
        List<ProgramDto> programs = new ArrayList<>();
        for (Program program: machine.getPrograms()) {
            programs.add(DtoFactory.programToDto(program));
        }
        HashMap<String, Object> result = new HashMap<>();
        result.put("Programs",programs );
        result.put("Account Balance", accountService.getAccountByUser(u).getBalance());
        return ResponseEntity.ok(result);
    }

    /**
     * deauthenticates user from a machine
     * @return
     */
    @RequestMapping(path="/machine/logout", method = RequestMethod.POST)
    public void userLogout(){
        Machine machine = getMachineByAuthentication();
        try {
            machine.idle();
            machineService.update(machine);
        }
        catch(StateTransitionException exception){
            //TODO return failed code
        }

    }

    @RequestMapping(path="/machine/startProgram", method = RequestMethod.POST)
    public ResponseEntity<?> startProgram(@RequestParam("programId") long programId){
        Machine machine = getMachineByAuthentication();
        if(!machine.containsProgram(programId))return ResponseEntity.status(400).body("Machine doesn't have this program");
        Program p = machine.getProgramById(programId);
        User u = userService.getUserByID(machine.getLoggedInUserId());
        if(u.getAccount().getBalance()<p.getCost())return ResponseEntity.status(400).body("Not enough funds");
        u.getAccount().substractBalance(p.getCost());
        u.getAccount().addPayment(new OneTimePayment(p.getCost()));
        try{
            machine.startMachine(machine.getLoggedInUserId(),programId);
        }catch (Exception e){
            return ResponseEntity.status(400).body("Machine not in the correct state");
        }
        userService.update(u);
        machineService.update(machine);
        Long endTime = machine.getProgramEndTime();
        HashMap<String, Long> result  = new HashMap<>();
        result.put("EndTime", endTime);
        return ResponseEntity.ok(result);
    }
    @RequestMapping(path="/machine/getEndTime", method = RequestMethod.GET)
    public ResponseEntity<?> getEndTime(){
        Machine machine = getMachineByAuthentication();
        if(!(machine.getState() instanceof RunningState)){
            return ResponseEntity.status(400).body("Machine is not running");
        }
        Long endTime = machine.getProgramEndTime();
        HashMap<String, Long> result  = new HashMap<>();
        result.put("EndTime", endTime);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(path="/machine/unlockMachine", method = RequestMethod.POST)
    public ResponseEntity<?> unlockMachine(@RequestParam("authItemToken") Long authItemToken){
        Machine machine = getMachineByAuthentication();
        User u = userService.getUserByAuthId(authItemToken);
        boolean status= false;
        if(u!= null)status = machine.unlockMachine(u.getId());
        HashMap<String, Object> result  = new HashMap<>();
        if(!status){
            result.put("Message","Someting went wrong ");
            result.put("status", false);
            return ResponseEntity.status(400).body(result);
        }
        machineService.update(machine);

        result.put("Message","Program Succesfully interupted");
        result.put("status", true);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(path="/machine/hello", method = RequestMethod.GET)
    public ResponseEntity<?> greetings() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String identifier = auth.getName();

        Machine machine = machineService.getMachineByIdentifier(identifier);

        return ResponseEntity.ok(machine);
    }

    private Machine getMachineByAuthentication(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String identifier = auth.getName();
        return machineService.getMachineByIdentifier(identifier);
    }

}
