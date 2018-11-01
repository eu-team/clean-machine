package euteam.cleanmachine.controller;

import euteam.cleanmachine.dto.TokenDto;
import euteam.cleanmachine.dto.UserSignInDto;
import euteam.cleanmachine.exceptions.AuthenticationException;
import euteam.cleanmachine.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @RequestMapping(path="/auth", method = RequestMethod.POST)
    public TokenDto createAuthenticationToken(@RequestBody UserSignInDto userSignInDto) throws AuthenticationException {

        // Authenticate the user throws exception if not able to to authenticate
        authenticate(userSignInDto.getUsername(), userSignInDto.getPassword());

        // Reload password post-security so we can generate the token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userSignInDto.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(token);

        return tokenDto;
    }

    private void authenticate(String username, String password) {
        
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("Disabled user", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Invalid credentials", e);
        }
    }
}
