package euteam.cleanmachine.controller;

import euteam.cleanmachine.dto.FacilityDto;
import euteam.cleanmachine.dto.MachineDto;
import euteam.cleanmachine.dto.NewFacilityDto;
import euteam.cleanmachine.dto.NewMachineDto;
import euteam.cleanmachine.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class FacilityController {

    @Autowired
    FacilityService facilityService;

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @RequestMapping(path="/facility", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> createFacility(@RequestBody @Valid NewFacilityDto newFacilityDto) {
        FacilityDto facilityDto = this.facilityService.addFacility(newFacilityDto);
        return ResponseEntity.ok().body(facilityDto);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @RequestMapping(path="/facility/{id}/machine", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> addMachineToFacility(@RequestBody @Valid NewMachineDto newMachineDto, @PathVariable String id) {
        MachineDto machineDto = facilityService.addNewMachineToFacility(Long.parseLong(id), newMachineDto);
        if (machineDto != null ) {
            return ResponseEntity.ok().body(machineDto);
        } else {
            return ResponseEntity.status(400).body(null);
        }

    }
}
