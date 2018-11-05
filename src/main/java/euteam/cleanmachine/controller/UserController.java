package euteam.cleanmachine.controller;

import euteam.cleanmachine.dto.UserDto;
import euteam.cleanmachine.dto.UserSignUpDto;
import euteam.cleanmachine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(path="/signup", method = RequestMethod.POST)
    public ResponseEntity<?> addUser( @RequestBody @Valid UserSignUpDto userSignUpDto) {
        UserDto userDto = userService.addUser(userSignUpDto);
        if(userDto != null) {
            return ResponseEntity.ok().body(userDto);
           // return("/result");
        } else {
            return ResponseEntity.status(400).body("Error while creating user");
        }

    }




}