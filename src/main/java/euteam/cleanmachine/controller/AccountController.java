package euteam.cleanmachine.controller;

import euteam.cleanmachine.model.user.Account;
import euteam.cleanmachine.model.user.User;
import euteam.cleanmachine.service.AccountService;
import euteam.cleanmachine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @PreAuthorize("hasAnyRole('CUSTOMER','ADMINISTRATOR')")
    @RequestMapping(path="/account/balance", method = RequestMethod.GET)
    public ResponseEntity<?> getAccountBalance(@RequestParam(value = "userId") long userId) {

        User user = userService.getUserByID(userId);
        double balance ;

        if (user == null) {
            return ResponseEntity.status(400).body("Error finding user");
        } else {
            balance = accountService.getUserBalance(user);
        }

        return ResponseEntity.ok("{\"balance\":"+balance+"}");
    }
}