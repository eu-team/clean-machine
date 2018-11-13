package euteam.cleanmachine.controller;

import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class MachineController {
    @Autowired
    private MachineService machineService;

    @RequestMapping(path="/machine/hello", method = RequestMethod.GET)
    public ResponseEntity<?> greetings() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String identifier = auth.getName();

        Machine machine = machineService.getMachineByIdentifier(identifier);

        return ResponseEntity.ok(machine);
    }
}
