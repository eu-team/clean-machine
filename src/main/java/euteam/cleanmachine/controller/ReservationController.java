package euteam.cleanmachine.controller;

import euteam.cleanmachine.dto.OneTimeReservationDto;
import euteam.cleanmachine.dto.ReserveOneTimeDto;
import euteam.cleanmachine.exceptions.ServiceException;
import euteam.cleanmachine.model.user.Customer;
import euteam.cleanmachine.model.user.User;
import euteam.cleanmachine.service.ReservationService;
import euteam.cleanmachine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class ReservationController {

    @Autowired
    UserService userService;

    @Autowired
    ReservationService reservationService;

    @PreAuthorize("hasRole('CUSOMER')")
    @RequestMapping(path="/reservation/onetime", method = RequestMethod.POST)
    public ResponseEntity<?> createOneTimeReservation(@RequestBody @Valid ReserveOneTimeDto reserveOneTimeDto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            Customer customer = (Customer) userService.getUserByUsername(username);

            OneTimeReservationDto oneTimeReservationDto = reservationService.createOneTimeReservation(customer, reserveOneTimeDto);

            return ResponseEntity.ok().body(oneTimeReservationDto);
        } catch (ServiceException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
