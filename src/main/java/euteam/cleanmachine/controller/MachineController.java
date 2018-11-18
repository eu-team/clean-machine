package euteam.cleanmachine.controller;

import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.service.AccountService;
import euteam.cleanmachine.service.MachineService;
import euteam.cleanmachine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
     *
     * @return
     */
    @RequestMapping(path = "/machine/getStatus", method = RequestMethod.GET)
    public ResponseEntity<?> getMachineStatus() {
        //Prepare result into object
        HashMap<String, String> result = new HashMap<>();
        result.put("status", machineService.getMachineStatus(getMachineIdByAuthentication()));
        return ResponseEntity.ok(result);
    }

    /**
     * A Customer or employe logs in on the machine
     *
     * @param authItemToken (USER token that can be found on card)
     * @return JSON
     */
    @RequestMapping(path = "/machine/login", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUserOnMachine(@RequestParam("authItemToken") Long authItemToken) {
        String machineID = getMachineIdByAuthentication();
        Long userID = userService.getUserIdByAuthId(authItemToken);

        if (!userService.doesUserExist(authItemToken)) {
            return ResponseEntity.status(400).body("No user found linked to this Authentication item");
        }
        if (!machineService.authenticateOnMachine(authItemToken, machineID)) {
            return ResponseEntity.status(400).body("Cannot change to authentication state at the moment");
        }

        HashMap<String, Object> result = new HashMap<>();
        result.put("Programs", machineService.getProgramsFromMachine(machineID));
        result.put("Account Balance", accountService.getUserBalanceById(userID));
        return ResponseEntity.ok(result);
    }

    /**
     * gives all programs and user balance, used to get to machine state after reboot;
     *
     * @return
     */
    @RequestMapping(path = "/machine/getLoggedInInfo", method = RequestMethod.GET)
    public ResponseEntity<?> getAuthenticateInfo() {
        String machineID = getMachineIdByAuthentication();
        Long userID = machineService.getLoggedInUserId(machineID);
        if (userID == null) {
            return ResponseEntity.status(400).body("No user logged in");
        }
        HashMap<String, Object> result = new HashMap<>();
        result.put("Programs", machineService.getProgramsFromMachine(machineID));
        result.put("Account Balance", accountService.getUserBalanceById(userID));
        return ResponseEntity.ok(result);
    }

    /**
     * deauthenticates user from a machine
     *
     * @return
     */
    @RequestMapping(path = "/machine/logout", method = RequestMethod.POST)
    public ResponseEntity<?> userLogout() {
        String machineID = getMachineIdByAuthentication();
        if (!machineService.machinelogout(machineID))return ResponseEntity.status(400).body("something went wrong during the logout process");
        return ResponseEntity.ok("Successfully logged out");
    }

    @RequestMapping(path = "/machine/startProgram", method = RequestMethod.POST)
    public ResponseEntity<?> startProgram(@RequestParam("programId") long programId) {
        String machineID = getMachineIdByAuthentication();
        if (!machineService.containsProgram(machineID,programId))
            return ResponseEntity.status(400).body("Machine doesn't have this program");
        boolean successful = machineService.startProgram(machineID,programId);
        if(!successful) return ResponseEntity.status(400).body("Not enough funds or Machine not in the correct state");

        Long endTime = machineService.getProgramEndTime(machineID);
        if(endTime==null){
            return ResponseEntity.status(400).body("Something serious went wrong, if you get this message then even I can't help you. God bless you");
        }
        HashMap<String, Long> result = new HashMap<>();
        result.put("EndTime", endTime);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(path = "/machine/getEndTime", method = RequestMethod.GET)
    public ResponseEntity<?> getEndTime() {
        String machineID = getMachineIdByAuthentication();
        Long endTime = machineService.getProgramEndTime(machineID);
        if(endTime==null){
            return ResponseEntity.status(400).body("Machine not in running State");
        }
        HashMap<String, Long> result = new HashMap<>();
        result.put("EndTime", endTime);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(path = "/machine/unlockMachine", method = RequestMethod.POST)
    public ResponseEntity<?> unlockMachine(@RequestParam("authItemToken") Long authItemToken) {
        String machineID = getMachineIdByAuthentication();
        boolean status = machineService.unlockMachine(machineID,authItemToken);
        HashMap<String, Object> result = new HashMap<>();
        if (!status) {
            result.put("Message", "Someting went wrong ");
            result.put("status", status);
            return ResponseEntity.status(400).body(result);
        }
        result.put("Message", "Program Succesfully interupted");
        result.put("status", status);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(path = "/machine/hello", method = RequestMethod.GET)
    public ResponseEntity<?> greetings() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String identifier = auth.getName();

        Machine machine = machineService.getMachineByIdentifier(identifier);

        return ResponseEntity.ok(machine);
    }

    private String getMachineIdByAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
