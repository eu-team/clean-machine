package euteam.cleanmachine.controller;

import euteam.cleanmachine.dto.DtoFactory;
import euteam.cleanmachine.dto.ProgramDto;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.facility.Program;
import euteam.cleanmachine.model.user.User;
import euteam.cleanmachine.service.AccountService;
import euteam.cleanmachine.service.MachineService;
import euteam.cleanmachine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
public class MachineController {

    @Autowired
    MachineService machineService;

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @RequestMapping(path="/machine/getPrograms", method = RequestMethod.GET)
    public Iterable<ProgramDto> getMachinePrograms(Long machineId){
        //TODO remove parameter and retrieve it from the security auth
        List<Program> programs =  machineService.getProgramsFromMachine(machineId);
        List<ProgramDto> result = new ArrayList<>();
        for (Program program: programs) {
            result.add(DtoFactory.programToDto(program));
        }
        return result;
    }

    @RequestMapping(path="/machine/getUserBalance", method = RequestMethod.GET)
    public ResponseEntity<Double> getUserBalance(Long authItemToken){
        User u = userService.getUserByAuthId(authItemToken);
        return ResponseEntity.ok().body(accountService.getAccountByUser(u).getBalance());
    }

    @RequestMapping(path="/machine/startProgram", method = RequestMethod.POST)
    public ResponseEntity<?> startProgram(long  programId,Long authItemToken){
        //TODO USE machine id from progrss
        Machine machine = machineService.getMachine(1L);
        if(!machine.containsProgram(programId))return ResponseEntity.status(400).body("Machine doesn't have this program");
        User u =userService.getUserByAuthId(authItemToken);
        /*
        if(!machine.isAuthenticatedWithUserId(u.getId()))return ResponseEntity.status(400).body("this user is not authenticated");
        machine.startMachine(programId);
        machineService.updateMachine(machine);
        */
        return ResponseEntity.ok("Program is running");
    }
    @RequestMapping(path="/machine/authUser", method = RequestMethod.GET)
    public ResponseEntity<?> authenticateUserOnMachine(Long machineId,Long authItemToken){
        //TODO remove parameter and retrieve it from the security auth
        Machine machine =  machineService.getMachine(machineId);
        User u =userService.getUserByAuthId(authItemToken);
        //Check if machine is available to login one the machine
        /*
        if(!machine.isGoodToAuthenticate(u.getRole()))return ResponseEntity.status(400).body("Can not authenticate on this machine");
        //set machine state to autenticated;
        machine.authenticate(u.getId());
        //update machine state in repo
        machineService.updateMachine(machine);
        */
        return ResponseEntity.ok("User authenticated on the machine");
    }

}
