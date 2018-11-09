package euteam.cleanmachine.security;

import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.security.machine.JwtMachine;
import euteam.cleanmachine.service.MachineService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    private JwtTokenUtil jwtTokenUtil;
    private String tokenHeader;
    private UserDetailsService userDetailsService;
    private MachineService machineService;

    public JwtAuthorizationTokenFilter(UserDetailsService userDetailsService, MachineService machineService, JwtTokenUtil jwtTokenUtil, String tokenHeader) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.tokenHeader = tokenHeader;
        this.machineService = machineService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestHeader = request.getHeader("Authorization");

        String authToken;

        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {

            authToken = requestHeader.substring(7);
            UserDetails userDetails = null;

            String subject = getSubjectFromToken(authToken);

            if (subject != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                userDetails = createUserDetails(authToken, subject);
            }

            setSecurityContext(userDetails, authToken, request);
        }

        chain.doFilter(request, response);
    }

    private String getSubjectFromToken(String authToken) {
        String subject = null;

        try {
            subject = jwtTokenUtil.getUsernameFromToken(authToken);
        } catch (IllegalArgumentException e) {
            logger.error("an error occurred during getting subject from token", e);
        } catch (ExpiredJwtException e) {
            logger.warn("the token is expired and not valid anymore", e);
        }

        return subject;
    }

    private UserDetails createUserDetails(String authToken, String subject) {
        UserDetails userDetails = null;
        if (jwtTokenUtil.getTypeFromToken(authToken).equals(TokenTypes.USER.name())) {
            userDetails = authenticateUser(subject);
        } else if (jwtTokenUtil.getTypeFromToken(authToken).equals(TokenTypes.MACHINE.name())) {
            userDetails = authenticateMachine(subject);
        }
        return userDetails;
    }

    public UserDetails authenticateUser(String username) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        return userDetails;
    }

    public UserDetails authenticateMachine(String identifier) {
        Machine machine = machineService.getMachineByIdentifier(identifier);

        UserDetails userDetails = new JwtMachine(machine);
        return userDetails;
    }

    private void setSecurityContext(UserDetails userDetails, String authToken, HttpServletRequest request) {
        if (userDetails != null && jwtTokenUtil.validateToken(authToken, userDetails)) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}