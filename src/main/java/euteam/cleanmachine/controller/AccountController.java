package euteam.cleanmachine.controller;

import euteam.cleanmachine.model.user.Account;
import euteam.cleanmachine.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:8100/account" )
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(path="/add", method = RequestMethod.POST)
    public Account addAccount(@RequestBody Account account, HttpServletRequest request) {
        // TODO retrieve user from jwt.
        return accountService.addAccount(account);
    }
}