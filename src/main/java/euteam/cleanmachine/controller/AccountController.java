package euteam.cleanmachine.controller;

import euteam.cleanmachine.dto.AccountSubscriptionDto;
import euteam.cleanmachine.dto.BalanceDto;
import euteam.cleanmachine.dto.SubscribeToPlanDto;
import euteam.cleanmachine.exceptions.ServiceException;
import euteam.cleanmachine.model.user.Account;
import euteam.cleanmachine.model.user.Customer;
import euteam.cleanmachine.model.user.User;
import euteam.cleanmachine.security.JwtTokenUtil;
import euteam.cleanmachine.service.AccountService;
import euteam.cleanmachine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<?> getAccountBalance() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userService.getUserByUsername(username);

            BalanceDto balanceDto = accountService.getUserBalance(user);
            return ResponseEntity.ok().body(balanceDto);
        } catch (ServiceException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @RequestMapping(path="/account/subscribe", method = RequestMethod.POST)
    public ResponseEntity<?> subscribeToPlan(@RequestBody @Valid SubscribeToPlanDto subscribeToPlanDto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userService.getUserByUsername(username);

            Account account = accountService.getAccountByUser(user);

            AccountSubscriptionDto accountSubscriptionDto
                    = accountService.subscribeToPlan(account.getId(), subscribeToPlanDto.getSubscriptionPlanID());

            return ResponseEntity.status(201).body(accountSubscriptionDto);
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