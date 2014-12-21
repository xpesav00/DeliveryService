package pa165.servicelayer.validation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Class-level validation annotation for Delivery
 * 
 * @author Drimal
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DeliveryConstraintValidator.class})
@Documented
public @interface DeliveryConstraint {
    String message() default "pa165.servicelayer.validation.DeliveryConstraint";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default{};
}
