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

            if (jwtTokenUtil.getTypeFromToken(authToken).equals("user")) {
                userDetails = authenticateUser(authToken, request, response);
            } else if (jwtTokenUtil.getTypeFromToken(authToken).equals("machine")) {
                userDetails = authenticateMachine(authToken, request, response);
            }

            if (userDetails != null && jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }

    public UserDetails authenticateUser(String authToken, HttpServletRequest request, HttpServletResponse response) {
        String username = null;

        try {
            username = jwtTokenUtil.getUsernameFromToken(authToken);
        } catch (IllegalArgumentException e) {
            logger.error("an error occured during getting username from token", e);
        } catch (ExpiredJwtException e) {
            logger.warn("the token is expired and not valid anymore", e);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.debug("security context was null, so authorizating user");

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            return userDetails;
        }
        return null;
    }

    public UserDetails authenticateMachine(String authToken, HttpServletRequest request, HttpServletResponse response) {
        String identifier = null;

        try {
            identifier = jwtTokenUtil.getMachineIdentifierFromToken(authToken);
        } catch (IllegalArgumentException e) {
            logger.error("an error occured during getting machine identifier from token", e);
        } catch (ExpiredJwtException e) {
            logger.warn("the token is expired and not valid anymore", e);
        }

        if (identifier != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.debug("security context was null, so authorizating machine");

            Machine machine = machineService.getMachineByIdentifier(identifier);

            UserDetails userDetails = new JwtMachine(machine);
            return userDetails;
        }
        return null;
    }
}