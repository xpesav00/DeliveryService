package pa165.deliveryservice.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import pa165.deliveryservice.api.UserService;
import pa165.deliveryservice.api.dto.UserDto;

/**
 * Checks the entered login credentials in user DB.
 * 
 * @author macinos
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final static org.slf4j.Logger log = LoggerFactory.getLogger(UserAuthenticationProvider.class);

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        MessageDigest digest;
        try {
            digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            if (userService.userMatcher(name, digest.digest())) {
                List<GrantedAuthority> grantedAuths = new ArrayList<>();
                UserDto user = userService.getUserByName(name);
                String role = user.getUserRole().toString();
                grantedAuths.add(new SimpleGrantedAuthority(role));
                Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
                return auth;
            }
        } catch (NoSuchAlgorithmException ex) {
            log.error("Password encoding exc.: No such algorithm.", ex);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
