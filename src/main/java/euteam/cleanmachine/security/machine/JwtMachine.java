package euteam.cleanmachine.security.machine;

import euteam.cleanmachine.model.facility.Machine;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class JwtMachine implements UserDetails {
    private String identifier;
    private final Collection<? extends GrantedAuthority> authorities;
    private static final String ROLE_NAME = "ROLE_MACHINE";

    public JwtMachine(Machine machine) {
        this.identifier = machine.getIdentifier();
        ArrayList<GrantedAuthority> newAuthorities = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.ROLE_NAME);
        newAuthorities.add(authority);
        this.authorities = newAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.identifier;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
