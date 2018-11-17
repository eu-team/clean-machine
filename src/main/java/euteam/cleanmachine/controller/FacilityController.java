package euteam.cleanmachine.controller;

import euteam.cleanmachine.dto.FacilityDto;
import euteam.cleanmachine.dto.NewFacilityDto;
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
    public ResponseEntity<?> getAccountBalance(@RequestBody @Valid NewFacilityDto newFacilityDto, HttpServletRequest request) {
        FacilityDto facilityDto = this.facilityService.addFacility(newFacilityDto);
        return ResponseEntity.ok().body(facilityDto);
    }
}
