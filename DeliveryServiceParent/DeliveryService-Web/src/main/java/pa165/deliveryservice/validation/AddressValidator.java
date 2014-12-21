/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.deliveryservice.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pa165.deliveryservice.api.dto.AddressDto;

/**
 * Validator for Address object
 * @author John
 */
public class AddressValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return AddressDto.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "city", "error.customer.address.city");
        ValidationUtils.rejectIfEmpty(errors, "street", "error.customer.address.street");
        ValidationUtils.rejectIfEmpty(errors, "postcode", "error.customer.address.postcode");
        AddressDto address = (AddressDto) o;
        String trimmed = StringUtils.deleteWhitespace(String.valueOf(address.getPostcode()));
        if(trimmed.length() != 5) {
            errors.rejectValue("postcode", "error.customer.address.postcode.length");
        }
    }
    
}
