package pa165.deliveryservice.web;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String doLogin(Model model) {
        model.addAttribute("user", new User());
        return "/login";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.GET)
    public String doLogin(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes,
            SessionStatus status, HttpServletRequest request, UriComponentsBuilder uriBuilder, Locale locale) {
//        if(user.getUsername().equals("user") && user.getPassword().equals("user")){
//            return "redirect:" + uriBuilder.path("").build();
//        }
//        return "/login";

//        String viewName = "login";
//        ModelAndView mav = new ModelAndView(viewName);
//        loginFormValidator.validate(user, result);
//        if (result.hasErrors()) {
//            return mav;
//        }
        UserDto userData = userService.getUserByName(user.getUsername());
//        status.setComplete();

        if (userData == null) {
//            mav.getModel().put("ERROR", "Invalid UserName and Password");
            return "/login";
        } else {
//            viewName = "welcome";
            request.getSession().setAttribute("LOGGEDIN_USER", userData);
        }
//        mav.setViewName(viewName);

        return "redirect:" + uriBuilder.path("").build();
    }
}
