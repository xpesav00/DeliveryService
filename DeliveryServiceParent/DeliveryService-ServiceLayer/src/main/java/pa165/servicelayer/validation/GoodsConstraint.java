package pa165.servicelayer.validation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Class-level validation annotation for Goods
 * 
 * @author Drimal
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {GoodsConstraintValidator.class})
@Documented
public @interface GoodsConstraint {
    
    String message() default "pa165.servicelayer.validation.GoodsConstraint";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default{};
    
}
