package euteam.cleanmachine.controller;

import java.util.List;
import euteam.cleanmachine.model.user.User;
import euteam.cleanmachine.dao.UserDao;
import euteam.cleanmachine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.ui.Model;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class MainController {

    @Autowired
    UserService userService;





    @PreAuthorize("hasRole('ADMINISTRATOR')")

    @RequestMapping(path="/hello", method = RequestMethod.GET)
    public ResponseEntity<?> greetings() {
        return ResponseEntity.ok("Ok");
    }

    @RequestMapping(path="/users", method = RequestMethod.GET)
    public Iterable<User> findUsers() {

        List<User> users = (List<User>) userService.findAll();

        return users;
    }




}
