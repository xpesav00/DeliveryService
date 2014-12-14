package pa165.servicelayer.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import pa165.deliveryservice.api.dto.GoodsDto;


/**
 * Validator for @GoodsConstraint annotation
 * @author Drimal
 */
public class GoodsConstraintValidator implements ConstraintValidator<GoodsConstraint, GoodsDto>{

    @Override
    public void initialize(GoodsConstraint a) {}

    @Override
    public boolean isValid(GoodsDto goods, ConstraintValidatorContext cvc) {
        //Pokud je cena validni, tak musi byt nastaven i nejaky prodejce
        boolean isValid = !(goods.getPrice() >= 1 && goods.getSeller().isEmpty());
                
        if ( !isValid ) {
            cvc.disableDefaultConstraintViolation();
            cvc.buildConstraintViolationWithTemplate("{pa165.servicelayer.validation.GoodsConstraint}")
                    .addPropertyNode("seller").addConstraintViolation();
        }
        return isValid;
    }
    
}
