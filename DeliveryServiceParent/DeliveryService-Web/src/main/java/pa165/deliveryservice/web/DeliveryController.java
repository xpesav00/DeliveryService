package pa165.deliveryservice.web;


import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import pa165.deliveryservice.entity.DeliveryStatus;
import pa165.servicelayer.dto.CustomerDto;
import pa165.servicelayer.dto.DeliveryDto;
import pa165.servicelayer.dto.GoodsDto;
import pa165.servicelayer.dto.PostmanDto;
import pa165.servicelayer.serviceInterface.CustomerService;
import pa165.servicelayer.serviceInterface.DeliveryService;
import pa165.servicelayer.serviceInterface.PostmanService;

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
    
//    @Autowired
//    private MessageSource messageSource;
    
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
        return "delivery/list";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes, Locale locale, UriComponentsBuilder uriBuilder){
        log.debug("delete({})", id);
        DeliveryDto delivery = deliveryService.findDelivery(id);
        deliveryService.deleteDelivery(delivery);
//        redirectAttributes.addFlashAttribute(
//                "message",
//                messageSource.getMessage("delivery.delete.message", new Object[]{delivery.getName(), delivery.getPostman(), delivery.getCustomer()}, locale)
//        );
        return "redirect:" + uriBuilder.path("/delivery/list").build();
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update_form(@PathVariable long id, Model model) {
        DeliveryDto delivery = deliveryService.findDelivery(id);
        model.addAttribute("delivery", delivery);
        log.debug("update_form(model={})", model);
        return "delivery/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute DeliveryDto delivery, BindingResult bindingResult, 
            RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {
        log.debug("update(locale={}, delivery={})", locale, delivery);
        if(delivery.getId() == 0){
            deliveryService.createDelivery(delivery);
        } else {
            deliveryService.updateDelivery(delivery);
        }
        
        return "redirect:" + uriBuilder.path("delivery/list").build();
    }
}
