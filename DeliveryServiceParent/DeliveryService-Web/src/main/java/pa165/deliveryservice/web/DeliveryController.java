package pa165.deliveryservice.web;


import java.util.List;
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
import pa165.servicelayer.dto.DeliveryDto;
import pa165.servicelayer.dto.GoodsDto;
import pa165.servicelayer.serviceInterface.DeliveryService;

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
    
    @ModelAttribute("deliveries")
    public List<DeliveryDto> allDeliveries() {
        log.debug("allDeliveries()");
        return deliveryService.getAllDeliveries();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("list()");
        model.addAttribute("delivery", new DeliveryDto());
        return "delivery/list";
    }
    
    @RequestMapping(value = "/list/{id}", method=RequestMethod.GET)
    public List<GoodsDto> allGoods(@PathVariable long id, Model model){
        log.debug("allGoods()");
        DeliveryDto delivery = deliveryService.findDelivery(id);
        return delivery.getGoods();
    }
}
