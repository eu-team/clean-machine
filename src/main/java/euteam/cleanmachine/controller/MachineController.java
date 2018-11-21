package euteam.cleanmachine.controller;

import euteam.cleanmachine.dto.UserDto;
import euteam.cleanmachine.exceptions.ServiceException;
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
import java.util.Map;

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
        HashMap<String, Object> result = new HashMap<>();
        try {
            UserDto user = machineService.authenticateOnMachine(authItemToken, machineID);
            result.put("Programs", machineService.getProgramsFromMachine(machineID));
            result.put("Account Balance", accountService.getUserBalanceById(user.getId()));
            return ResponseEntity.ok(result);
        } catch (ServiceException e) {
            result.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
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
        Map<String, String> response = new HashMap<>();
        try {
            String machineID = getMachineIdByAuthentication();
            machineService.machinelogout(machineID);
            response.put("message", "Successfully logged out");
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @RequestMapping(path = "/machine/startProgram", method = RequestMethod.POST)
    public ResponseEntity<?> startProgram(@RequestParam("programId") long programId) {
        String machineID = getMachineIdByAuthentication();
        try {
            Map<String, Long> response = new HashMap<>();
            Long endTime = machineService.startProgram(machineID, programId);
            response.put("EndTime", endTime);
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
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
        Map<String, Object> response = new HashMap<>();
        try {
            machineService.unlockMachine(machineID, authItemToken);
            response.put("Message", "Program Succesfully interupted");
            response.put("status", true);
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            response.put("error", e.getMessage());
            response.put("status", false);
            return ResponseEntity.badRequest().body(response);
        }

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
