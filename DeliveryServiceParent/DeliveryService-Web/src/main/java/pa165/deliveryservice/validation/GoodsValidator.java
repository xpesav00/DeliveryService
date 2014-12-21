package pa165.deliveryservice.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pa165.deliveryservice.api.dto.GoodsDto;

/**
 * Validator for Goods object
 * @author Drimal
 */
public class GoodsValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return GoodsDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "seller", "error.goods.seller.empty");
        GoodsDto goods = (GoodsDto) target;
        if(goods.getPrice() <= 0){
            errors.rejectValue("price", "error.goods.price.negativevalue");
        }
        if(goods.getSeller().length() < 2 || goods.getSeller().length() > 30){
            errors.rejectValue("seller", "error.goods.seller.length");
        }
    }
    
}
