package euteam.cleanmachine.controller;

import euteam.cleanmachine.dto.UserDto;
import euteam.cleanmachine.model.user.User;
import euteam.cleanmachine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8100/account" )
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(path="/user/add", method = RequestMethod.POST)
    public UserDto addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}