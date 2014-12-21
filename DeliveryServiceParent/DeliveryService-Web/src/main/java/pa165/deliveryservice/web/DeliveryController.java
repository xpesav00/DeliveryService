package pa165.deliveryservice.web;


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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import pa165.deliveryservice.entity.DeliveryStatus;
import pa165.deliveryservice.api.dto.CustomerDto;
import pa165.deliveryservice.api.dto.DeliveryDto;
import pa165.deliveryservice.api.dto.PostmanDto;
import pa165.deliveryservice.api.CustomerService;
import pa165.deliveryservice.api.DeliveryService;
import pa165.deliveryservice.api.PostmanService;
import pa165.deliveryservice.validation.DeliveryValidator;

/**
 * SpringMVC Controller for handling deliveries.
 *
 * @author Drimal
 */
@Controller
@RequestMapping("/delivery")
public class DeliveryController {
    private final static Logger log = LoggerFactory.getLogger(DeliveryController.class);
    
    @Autowired
    private DeliveryService deliveryService;
    
    @Autowired
    private PostmanService postmanService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private MessageSource messageSource;
    
    @ModelAttribute("deliveries")
    public List<DeliveryDto> allDeliveries() {
        log.debug("allDeliveries()");
        return deliveryService.getAllDeliveries();
    }
    
    @ModelAttribute("postmen")
    public List<PostmanDto> allPostmen() {
        log.debug("allPostmen()");
        return postmanService.getAllPostmen();
    }
    
    @ModelAttribute("customers")
    public List<CustomerDto> allCustomers() {
        log.debug("allCustomers()");
        return customerService.getAllCustomers();
    }

    @ModelAttribute("status")
    public DeliveryStatus[] status() {
        log.debug("status()");
        return DeliveryStatus.values();
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("list()");
        model.addAttribute("delivery", new DeliveryDto());
        return "/delivery/list";
    }
    
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public String postmanDeliveries(@PathVariable long id, Model model) {
        log.debug("postmanDeliveries");
        model.addAttribute("delivery", new DeliveryDto());
        model.addAttribute("deliveries", postmanService.findPostman(id).getDeliveries());
        return "/delivery/list";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes, Locale locale, UriComponentsBuilder uriBuilder){
        log.debug("delete({})", id);
        DeliveryDto delivery = deliveryService.findDelivery(id);
        deliveryService.deleteDelivery(delivery);
        redirectAttributes.addFlashAttribute(
                "message",
                messageSource.getMessage("delivery.delete.message", new Object[]{delivery.getName(), delivery.getId()}, locale)
        );
        return "redirect:" + uriBuilder.path("/delivery/list").build();
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update_form(@PathVariable long id, Model model) {
        DeliveryDto delivery = deliveryService.findDelivery(id);
        model.addAttribute("delivery", delivery);
        log.debug("update_form(model={})", model);
        
        
        return "/delivery/edit";
    }
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new DeliveryValidator());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("delivery") DeliveryDto delivery, BindingResult bindingResult, 
            RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {
        
        if (bindingResult.hasErrors()) {
            log.debug("binding errors");
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.debug("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                log.debug("FieldError: {}", fe);
            }//TODO fix return (wrong redirect in web)
            return (Long.valueOf(delivery.getId())==null)?"delivery/list":"delivery/edit";
        }
        
        PostmanDto postman = postmanService.findPostman(delivery.getPostman().getId());
        CustomerDto customer = customerService.findCustomer(delivery.getCustomer().getId());
        delivery.setPostman(postman);
        delivery.setCustomer(customer);
        postman.addDelivery(delivery);
        customer.addDelivery(delivery);
        if(delivery.getId() == 0){
            log.debug("create(locale={}, delivery={}, postman={})", locale, delivery, delivery.getPostman());
            deliveryService.createDelivery(delivery);
        } else {
            log.debug("update(locale={}, delivery={}, postman={})", locale, delivery, delivery.getPostman());
            deliveryService.updateDelivery(delivery);
            
            redirectAttributes.addFlashAttribute(
                "message",
                messageSource.getMessage("delivery.update.message", new Object[]{delivery.getName(), delivery.getId()}, locale)
            );
        }
        
        return "redirect:" + uriBuilder.path("/delivery/list").build();
    }
}
