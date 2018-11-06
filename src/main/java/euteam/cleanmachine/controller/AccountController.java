package euteam.cleanmachine.controller;

import euteam.cleanmachine.model.user.Account;
import euteam.cleanmachine.model.user.User;
import euteam.cleanmachine.security.JwtTokenUtil;
import euteam.cleanmachine.service.AccountService;
import euteam.cleanmachine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class AccountController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @RequestMapping(path="/account/balance", method = RequestMethod.GET)
    public ResponseEntity<?> getAccountBalance(HttpServletRequest request) {

        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.getUserByUsername(username);
        double balance;

        if (user == null) {
            return ResponseEntity.status(400).body("Error finding user");
        } else {
            balance = accountService.getUserBalance(user);
        }

        return ResponseEntity.ok("{\"balance\":"+balance+"}");
    }
}