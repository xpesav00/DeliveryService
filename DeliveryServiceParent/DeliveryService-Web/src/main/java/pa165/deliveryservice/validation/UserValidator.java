/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.deliveryservice.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pa165.deliveryservice.api.UserService;
import pa165.deliveryservice.api.dto.UserDto;

/**
 *
 * @author JStastny
 */
public class UserValidator implements Validator {
    private Pattern pattern;
    private Matcher matcher;
    private UserService userService;
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
     
    public UserValidator(UserService userService) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        this.userService = userService;
    }
    
    /**
	   * Validate password with regular expression
	   * @param password password for validation
	   * @return true valid password, false invalid password
	   */
	  public boolean validate(final String password){
 
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
        UserDto user = (UserDto)o;
        if(user.getPassword().length < 6) {
            errors.rejectValue("password", "error.login.password.length");
        } //toto projde v pohode, kdyz je helo kratsi jak 6 tak to vyhodi chybu
        UserDto userByName = userService.getUserByName(user.getUsername());
        if(userByName != null) {
            errors.rejectValue("username", "error.login.user.exists");
        }
        
    }
    
}
