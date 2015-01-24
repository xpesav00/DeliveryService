package pa165.deliveryservice.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * SpringMVC Controller for login.
 *
 * @author Drimal
 */
@Controller
@RequestMapping()
public class LoginController {

    private final static Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        return "/login";
    }

    @RequestMapping(value = "/login_fail", method = RequestMethod.GET)
    public String loginFail() {

        return "/login_fail";
    }
}
