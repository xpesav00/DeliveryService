package pa165.deliveryservice.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import pa165.deliveryservice.api.CustomerService;
import pa165.deliveryservice.api.DeliveryService;
import pa165.deliveryservice.api.dto.CustomerDto;
import pa165.deliveryservice.api.dto.DeliveryDto;
import pa165.deliveryservice.validation.AddressValidator;
import pa165.deliveryservice.validation.CustomerValidator;


/**
 * SpringMVC Controller for handling customers
 *
 * @author John
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    final static Logger log = LoggerFactory.getLogger(CustomerController.class);
    
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private MessageSource messageSource;
    
    @ModelAttribute("customers")
    public List<CustomerDto> allBook() {
        log.debug("allCustomers()");
        return customerService.getAllCustomers();
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("calling list() method");
        model.addAttribute("customer", new CustomerDto());
        return "customer/list";
    }   
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes, Locale locale, UriComponentsBuilder uriBuilder) {
        log.debug("calling delete({}) method", id);
        CustomerDto customer = customerService.findCustomer(id);
        customerService.deleteCustomer(customer);
        redirectAttributes.addFlashAttribute(
                "message",
                messageSource.getMessage("message.delete.customer", new Object[]{customer.getFirstName(),customer.getLastName(),customer.getId()}, locale)
        );
        return "redirect:" + uriBuilder.path("/customer/list").build();
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update_form(@PathVariable long id, Model model) {
        CustomerDto customer = customerService.findCustomer(id);
        model.addAttribute("customer", customer);
        log.debug("update_form(model={})", model);
        return "customer/edit";
    }
    
    @RequestMapping(value = "/deliveries/{id}", method = RequestMethod.GET)
    public String showDeliveries(@PathVariable long id, Model model){
        log.debug("showDeliveries()");
        CustomerDto customer = customerService.findCustomer(id);
        
        List<DeliveryDto> allDeliveries = deliveryService.getAllDeliveries();
        List<DeliveryDto> freeDeliveries = new ArrayList<>();
        for(DeliveryDto currentDelivery:allDeliveries){
            if(currentDelivery.getCustomer() == null) {
                freeDeliveries.add(currentDelivery);
            }
        }
        model.addAttribute("customerDeliveries", customer.getDeliveries());
        model.addAttribute("allDeliveries",freeDeliveries);
        return "customer/deliveries";
    }
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new CustomerValidator(new AddressValidator()));
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("customer") CustomerDto customer, BindingResult bindingResult, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {
        log.debug("update(locale={}, customer={})", locale, customer);
        if (bindingResult.hasErrors()) {
            log.debug("binding errors");
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.debug("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                log.debug("FieldError: {}", fe);
            }
            return (Long.valueOf(customer.getId()) == 0)?"customer/list":"customer/edit";
        }
        if (Long.valueOf(customer.getId()) == null) {
            customerService.createCustomer(customer.getFirstName(), customer.getLastName(), customer.getAddress(), customer.getDeliveries());
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("message.new.customer", new Object[]{customer.getFirstName(), customer.getLastName(),customer.getAddress()}, locale)
            );
        } else {
            customerService.updateCustomer(customer);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("message.update.customer", new Object[]{customer.getFirstName(), customer.getLastName()}, locale)
            );
        }
        return "redirect:" + uriBuilder.path("/customer/list").build();
    }
    
    
}
