package euteam.cleanmachine.controller;

import euteam.cleanmachine.dto.MachineAuthDto;
import euteam.cleanmachine.dto.TokenDto;
import euteam.cleanmachine.dto.UserSignInDto;
import euteam.cleanmachine.exceptions.AuthenticationException;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.security.JwtTokenUtil;
import euteam.cleanmachine.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MachineService machineService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @RequestMapping(path="/auth/user", method = RequestMethod.POST)
    public TokenDto createAuthenticationToken(@RequestBody UserSignInDto userSignInDto) throws AuthenticationException {

        // Authenticate the user throws exception if not able to to authenticate
        authenticateUser(userSignInDto.getUsername(), userSignInDto.getPassword());

        // Reload password post-security so we can generate the token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userSignInDto.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(token);

        return tokenDto;
    }
    @CrossOrigin
    @RequestMapping(path="/auth/machine", method = RequestMethod.POST)
    public ResponseEntity<?> authMachine(@RequestBody @Valid MachineAuthDto machineAuthDto) {
        String token = authenticateMachine(machineAuthDto.getIdentifier());
        Map<String, Object> response = new HashMap<String, Object>();
        if(token == null) {
            response.put("error", "Unknown machine");
            return ResponseEntity.status(401).body(response);
        } else {
            response.put("token", token);
            return ResponseEntity.ok(response);
        }
    }

    private void authenticateUser(String username, String password) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("Disabled user", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Invalid credentials", e);
        }
    }

    private String authenticateMachine(String identifier) {
        Machine machine = machineService.getMachineByIdentifier(identifier);
        if(machine == null) {
            return null;
        }
        return jwtTokenUtil.generateToken(machine);
    }
}
