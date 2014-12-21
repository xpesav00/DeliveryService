package pa165.deliveryservice.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pa165.deliveryservice.api.dto.DeliveryDto;
import pa165.deliveryservice.entity.DeliveryStatus;

/**
 * Validator for Delivery object
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
        if(delivery.getPostman().getId() == -1){
            errors.rejectValue("postman", "error.delivery.postman");
        }
        if(delivery.getCustomer().getId() == -1){
            errors.rejectValue("customer", "error.delivery.customer");
        }
        if (delivery.getStatus() == DeliveryStatus.NONE) {
            errors.rejectValue("status", "error.delivery.status.empty");
        }
        if(delivery.getId() == 0 && delivery.getStatus() != DeliveryStatus.INIT){
            errors.rejectValue("status", "error.delivery.status");
        }
    }
}
