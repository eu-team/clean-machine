package euteam.cleanmachine.controller;

import euteam.cleanmachine.dto.NFCDto;
import euteam.cleanmachine.dto.UserDto;
import euteam.cleanmachine.dto.UserSignUpDto;
import euteam.cleanmachine.model.user.User;
import euteam.cleanmachine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(path="/signup", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody @Valid UserSignUpDto userSignUpDto) {
        try {
            UserDto userDto = userService.addUser(userSignUpDto);
            return ResponseEntity.ok().body(userDto);
        } catch (SecurityException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @RequestMapping(path="/linkcard", method = RequestMethod.POST)
    public ResponseEntity<?> LinkCard(@RequestBody @Valid NFCDto cardDto, String Username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //bool method
        userService.LinkCard(Username,cardDto);
        // UserDto userDto= userService.LinkCard(Username,cardDto);
        String identifier = auth.getName();

        if(identifier != null){
            return ResponseEntity.ok().body(cardDto);
        } else {
            return ResponseEntity.status(400).body("Error while linking card");
        }
    }
//


}