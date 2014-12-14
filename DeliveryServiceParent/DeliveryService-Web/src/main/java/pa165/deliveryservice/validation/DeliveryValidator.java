package pa165.deliveryservice.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pa165.deliveryservice.api.dto.DeliveryDto;

/**
 *
 * @author Drimal
 */
public class DeliveryValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return DeliveryDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.delivery.name");
        DeliveryDto delivery = (DeliveryDto) target;
        if(delivery.getPostman() == null){
            errors.reject("postman", "error.delivery.postman");
        }
        if(delivery.getCustomer() == null){
            errors.reject("customer", "error.delivery.customer");
        }
    }
    
}
