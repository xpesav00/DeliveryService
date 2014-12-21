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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import pa165.deliveryservice.api.DeliveryService;
import pa165.deliveryservice.api.PostmanService;
import pa165.deliveryservice.api.dto.DeliveryDto;
import pa165.deliveryservice.api.dto.GoodsDto;
import pa165.deliveryservice.api.dto.PostmanDto;
import pa165.deliveryservice.validation.PostmanValidator;

/**
 * SpringMVC Controller for handling postmen.
 *
 * @author Martin Nekula, John
 */
@Controller
@RequestMapping("/postman")
public class PostmanController {
    private final static Logger log = LoggerFactory.getLogger(PostmanController.class);
    
    @Autowired
    private PostmanService postmanService;
    @Autowired
    private DeliveryService deliveryService;
    
    @Autowired
    private MessageSource messageSource;

    
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
        model.addAttribute("deliveries", postman.getDeliveries());
        log.debug("update_form(model={})", model);
        return "postman/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {
        PostmanDto postman = postmanService.findPostman(id);
        postmanService.deletePostman(postman);
        
        redirectAttributes.addFlashAttribute(
                "message",
                messageSource.getMessage("message.delete.postman", new Object[]{postman.getFirstName(), postman.getLastName(), postman.getId()}, locale)
        );
        return "redirect:" + uriBuilder.path("/postman/list").build();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("postman") PostmanDto postman, BindingResult bindingResult, 
            RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {
        log.debug("update(locale={}, postman={})", locale, postman);
        if (bindingResult.hasErrors()) {
            log.debug("binding errors");
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.debug("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                log.debug("FieldError: {}", fe);
            }//TODO fix return (wrong redirect in web)
            return (Long.valueOf(postman.getId()) ==null)?"postman/list":"postman/edit";
        }
        if(postman.getId() == 0){
            if(postman.areDeliveriesNull()){
                postman.setDeliveries(new ArrayList<DeliveryDto>());
            }
            postmanService.addPostman(postman);
        } else {
            if(postman.areDeliveriesNull()){
                postman.setDeliveries(new ArrayList<DeliveryDto>());
            }
            postmanService.updatePostman(postman);
            
            redirectAttributes.addFlashAttribute(
                "message",
                messageSource.getMessage("message.update.postman", new Object[]{postman.getFirstName(), postman.getLastName()}, locale)
            );
        }
        
        return "redirect:" + uriBuilder.path("/postman/list/").build();
    }
    
    @RequestMapping(value = "/deliveries/{id}", method = RequestMethod.GET)
    public String showDeliveries(@PathVariable long id, Model model){
        log.debug("showDeliveries()");
        PostmanDto postman = postmanService.findPostman(id);
        model.addAttribute("postmansDeliveries",postman.getDeliveries());
        return "postman/deliveries";
    }
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new PostmanValidator());
    }
}
