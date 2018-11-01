package euteam.cleanmachine.security;

import euteam.cleanmachine.model.user.User;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
    }
}