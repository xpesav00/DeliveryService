package pa165.deliveryservice.web;

import java.security.Principal;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import pa165.deliveryservice.api.UserService;
import pa165.deliveryservice.api.dto.UserDto;

/**
 *
 * @author Drimal
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private final static Logger log = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping(method = RequestMethod.GET)
    public String login(ModelMap model) {

        return "/login";
    }

//    @RequestMapping(value = "/loginError", method = RequestMethod.GET)
//    public String loginError(ModelMap model) {
//        model.addAttribute("error", "true");
//        
//        return "login_page";
//    }

//        return "redirect:" + uriBuilder.path("").build();
}
