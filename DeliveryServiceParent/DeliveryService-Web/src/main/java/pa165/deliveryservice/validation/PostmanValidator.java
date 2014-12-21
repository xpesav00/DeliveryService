package pa165.deliveryservice.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pa165.deliveryservice.api.dto.PostmanDto;

/**
 *
 * @author Drimal
 */
public class PostmanValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return PostmanDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "firstName", "error.postman.firstname.empty");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "error.postman.lastname.empty");
        PostmanDto postman = (PostmanDto) target;
        if(postman.getFirstName().length() < 2 || postman.getFirstName().length() > 30){
            errors.rejectValue("firstName", "error.postman.firstname.length");
        }
        if(postman.getLastName().length() < 2 || postman.getLastName().length() > 30){
            errors.rejectValue("lastName", "error.postman.lastname.length");
        }
    }
    
}
