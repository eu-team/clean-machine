package euteam.cleanmachine.controller;

import euteam.cleanmachine.dto.UserDto;
import euteam.cleanmachine.dto.UserSignUpDto;
import euteam.cleanmachine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}