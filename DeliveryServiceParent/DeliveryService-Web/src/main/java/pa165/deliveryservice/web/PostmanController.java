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
import pa165.servicelayer.dto.DeliveryDto;
import pa165.servicelayer.dto.GoodsDto;
import pa165.servicelayer.dto.PostmanDto;
import pa165.servicelayer.serviceInterface.DeliveryService;
import pa165.servicelayer.serviceInterface.PostmanService;

/**
 * SpringMVC Controller for handling postmen.
 *
 * @author Martin Nekula
 */
@Controller
@RequestMapping("/postman")
public class PostmanController {
    private final static Logger log = LoggerFactory.getLogger(DeliveryController.class);
    
    @Autowired
    private PostmanService postmanService;

    
    @ModelAttribute("postmen")
    public List<PostmanDto> allDeliveries() {
        log.debug("allPostmen()");
        return postmanService.getAllPostmen();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("list()");
        model.addAttribute("postman", new PostmanDto());
        return "postman/list";
    }

    @RequestMapping(value = "/list/{id}", method=RequestMethod.GET)
    public String allDeliveries(@PathVariable long id, Model model){
        log.debug("all Deliveries for Postman");
        model.addAttribute("postman", new PostmanDto());
        PostmanDto postman = postmanService.findPostman(id);
        model.addAttribute("deliveries", postman.getDeliveries());
        return "postman/list";
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update_form(@PathVariable long id, Model model) {
        PostmanDto postman = postmanService.findPostman(id);
        model.addAttribute("postman", postman);
        //model.addAttribute("deliveries", deliveryService.getAllDeliveries());
        log.debug("update_form(model={})", model);
        return "postman/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id, Model model) {
        PostmanDto postman = postmanService.findPostman(id);
        model.addAttribute("postman", postman);
        postmanService.deletePostman(postman);
        return "postman/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute PostmanDto postman, BindingResult bindingResult, 
            RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {
        log.debug("update(locale={}, postman={})", locale, postman);
        if(postman.getId() == 0){
            postmanService.addPostman(postman);
        } else {
            postmanService.updatePostman(postman);
        }
        
        return "redirect:" + uriBuilder.path("/postman/list/").build();
    }
}
