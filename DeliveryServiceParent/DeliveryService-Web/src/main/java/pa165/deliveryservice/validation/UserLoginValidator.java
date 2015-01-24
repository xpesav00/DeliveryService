package pa165.deliveryservice.validation;

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
public class UserLoginValidator implements Validator {
    private UserService userService;
    
    public UserLoginValidator(UserService userService) {
        this.userService = userService;
    } 
    
    @Override
    public boolean supports(Class<?> type) {
        return UserDto.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.login.username");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.login.password");
        UserDto user = (UserDto) o;        
        boolean isThatYou = userService.userMatcher(user.getUsername(), user.getPassword());
        if(!isThatYou) {
            errors.rejectValue("formError", "error.login.wrongData");
        }
    }
    
}
