package pa165.deliveryservice.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pa165.servicelayer.dto.GoodsDto;

/**
 *
 * @author Drimal
 */
public class GoodsValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return GoodsDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
