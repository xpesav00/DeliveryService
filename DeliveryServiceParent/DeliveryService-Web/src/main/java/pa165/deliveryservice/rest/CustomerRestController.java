package pa165.deliveryservice.rest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.core.Context;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pa165.deliveryservice.api.CustomerService;
import pa165.deliveryservice.api.UserService;
import pa165.deliveryservice.api.dto.AddressDto;
import pa165.deliveryservice.api.dto.CustomerDto;
import pa165.deliveryservice.rest.entity.Address;
import pa165.deliveryservice.rest.entity.Customer;

/**
 * Rest controller for Customer
 * @author Drimal
 */
@RestController
@RequestMapping("/rest/customer")
public class CustomerRestController{
    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void authenticateUser(@RequestHeader HttpHeaders headers){
        RestAuthenticater authenticater = new RestAuthenticater();
        authenticater.authenticateAndAuthorizeRemoteRestUser(SecurityContextHolder.getContext(), headers, userService);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<CustomerDto> findAll(@RequestHeader HttpHeaders headers){
        List<CustomerDto> resultList = new ArrayList<>();
        List<CustomerDto> allCustomers = customerService.getAllCustomers();
        for(CustomerDto customerDto : allCustomers){
            resultList.add(retrieveCustomerDtoForJson(customerDto));
        }
        return resultList;
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    @ResponseBody
    public CustomerDto getCustomer(@PathVariable(value = "customerId") long id) {
        CustomerDto findCustomer = customerService.findCustomer(id);
        findCustomer.setDeliveries(Collections.EMPTY_LIST);
        return findCustomer;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody Customer customer) {
        CustomerDto customerDto = convertCustomerToCustomerDto(customer);
        customerService.createCustomer(customerDto.getFirstName(), customerDto.getLastName(), customerDto.getAddress(), Collections.EMPTY_LIST);
    }
    
    @RequestMapping(value = "/update/{customerId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void update(@PathVariable("customerId") long id, @RequestBody Customer customer) {
        CustomerDto customerDto = convertCustomerToCustomerDto(customer);
        CustomerDto customerDB = customerService.findCustomer(customerDto.getId());
        customerDto.setDeliveries(customerDB.getDeliveries());
        customerService.updateCustomer(customerDto);
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id){
        CustomerDto customerDto = customerService.findCustomer(id);
        customerService.deleteCustomer(customerDto);
    }
    
    private CustomerDto retrieveCustomerDtoForJson(CustomerDto customer){
        CustomerDto cust = new CustomerDto();
        cust.setId(customer.getId());
        cust.setFirstName(customer.getFirstName());
        cust.setLastName(customer.getLastName());
        cust.setAddress(customer.getAddress());
        cust.setDeliveries(Collections.EMPTY_LIST);
        
        return cust;
    }
    
    private CustomerDto convertCustomerToCustomerDto(Customer customer){
        Address address = customer.getAddress();
        
        AddressDto addressDto = new AddressDto();
        addressDto.setCity(address.getCity());
        addressDto.setStreet(address.getStreet());
        addressDto.setPostcode(address.getPostcode());
        
        CustomerDto cust = new CustomerDto();
        cust.setId(customer.getId());
        cust.setFirstName(customer.getFirstName());
        cust.setLastName(customer.getLastName());
        cust.setAddress(addressDto);
        
        return cust;
    }
}
