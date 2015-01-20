package pa165.deliveryservice.web;

import java.util.Locale;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Drimal
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @ModelAttribute("user")
    public User getUser(){
        return new User();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String doLogin(Model model){
        model.addAttribute("user", new User());
        return "/login";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.GET)
    public String doLogin(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale){
        if(user.getUsername().equals("user") && user.getPassword().equals("user")){
            return "redirect:" + uriBuilder.path("").build();
        }
        return "/login";
    }
}

class User{
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
