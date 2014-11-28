/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.deliveryservice.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pa165.servicelayer.dto.AddressDto;
import pa165.servicelayer.dto.CustomerDto;

/**
 *
 * @author John
 */
public class CustomerValidator implements Validator {
    private final Validator addressValidator;
    final static Logger log = LoggerFactory.getLogger(CustomerValidator.class);

    public CustomerValidator(Validator addressValidator) {
        if(addressValidator == null) {
            log.error("Checking addressValidator and is NULL.");
            throw new IllegalArgumentException("The supplied [Validator] is required and must not be null.");
        }
        
        if(!addressValidator.supports(AddressDto.class)) {
            log.error("Checking addressValidator and is not supperted.");
            throw new IllegalArgumentException("The supplied [Validator] must support the validation of [Address] instances.");
        }
        this.addressValidator = addressValidator;
    }
    
    @Override
    public boolean supports(Class<?> type) {
        return CustomerDto.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "firstName", "error.common.name");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "error.common.surname");
        CustomerDto customer = (CustomerDto) o;
        try{
            errors.pushNestedPath("address");
            ValidationUtils.invokeValidator(this.addressValidator, customer.getAddress(), errors);
        }finally{
            errors.popNestedPath();
        }
    }
    
}
