package pa165.deliveryservice.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pa165.deliveryservice.api.UserService;
import pa165.deliveryservice.api.dto.UserDto;

/**
 *
 * @author JStastny
 */
@Component
public class UserUpdateValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher;
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    @Autowired
    private UserService userService;

    public UserUpdateValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    /**
     * Validate password with regular expression
     *
     * @param password password for validation
     * @return true valid password, false invalid password
     */
    public boolean validate(final String password) {

        matcher = pattern.matcher(password);
        return matcher.matches();

    }

    @Override
    public boolean supports(Class<?> type) {
        return UserDto.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.login.username");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.login.password");
        UserDto user = (UserDto) o;
        if (user.getPassword().length < 6) {
            errors.rejectValue("password", "error.login.password.length");
        }
        UserDto userByName = userService.getUserByName(user.getUsername());
        //pokud dostanu sam sebe, tak OK. Pokud dostanu existujiciho uzivatele tak NOK
        if (userByName != null && !(userByName.getUsername().equals(user.getUsername()))) {
            errors.rejectValue("username", "error.login.user.exists");
        }
    }

}
