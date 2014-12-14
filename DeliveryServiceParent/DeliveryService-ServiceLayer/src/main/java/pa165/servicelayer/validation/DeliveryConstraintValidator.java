package pa165.servicelayer.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import pa165.deliveryservice.entity.DeliveryStatus;
import pa165.deliveryservice.api.dto.DeliveryDto;


/**
 *
 * @author Drimal
 */
public class DeliveryConstraintValidator implements ConstraintValidator<DeliveryConstraint, DeliveryDto>{

    @Override
    public void initialize(DeliveryConstraint constraintAnnotation) {}

    @Override
    public boolean isValid(DeliveryDto delivery, ConstraintValidatorContext cvc) {
        
        boolean isValid = !delivery.getName().isEmpty() && delivery.getStatus() != DeliveryStatus.INIT;
                
        if ( !isValid ) {
            cvc.disableDefaultConstraintViolation();
            cvc.buildConstraintViolationWithTemplate("{pa165.servicelayer.validation.DeliveryConstraint}")
                    .addPropertyNode("status").addConstraintViolation();
        }
        return isValid;
    }
    
}
