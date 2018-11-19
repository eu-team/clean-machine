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

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(path="/signup", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody @Valid UserSignUpDto userSignUpDto) {
        UserDto userDto = userService.addUser(userSignUpDto);
        if(userDto != null) {
            return ResponseEntity.ok().body(userDto);
        } else {
            return ResponseEntity.status(400).body("Error while creating user");
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