package euteam.cleanmachine.security;

import euteam.cleanmachine.model.user.Role;
import euteam.cleanmachine.model.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthority(user.getRole())
        );
    }

    private static GrantedAuthority mapToGrantedAuthority(Role role) {
        return new SimpleGrantedAuthority(role.getRoleName().name());
    }
}