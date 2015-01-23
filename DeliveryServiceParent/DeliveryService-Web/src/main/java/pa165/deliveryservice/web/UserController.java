/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import pa165.deliveryservice.api.UserService;
import pa165.deliveryservice.api.dto.UserDto;
import pa165.deliveryservice.entity.UserRole;
import pa165.deliveryservice.validation.UserValidator;

/**
 *
 * @author JStastny
 */
@Controller
@RequestMapping("/user")
public class UserController {
    final static Logger log = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;
    
    @ModelAttribute("users")
    public List<UserDto> allUsers() {
        log.debug("allUsers()");
        return userService.retrieveAllUsers();
    }
    
    @ModelAttribute("role")
    public UserRole[] role() {
        log.debug("user role()");
        return UserRole.values();
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("calling list() method");
        model.addAttribute("user", new UserDto());
        return "user/list";
    } 
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes, Locale locale, UriComponentsBuilder uriBuilder) {
        log.debug("calling delete({}) method", id);
        UserDto user = userService.findUserById(id);
        userService.deleteUser(user);
        redirectAttributes.addFlashAttribute(
                "message",
                messageSource.getMessage("message.delete.user", new Object[]{user.getUsername()}, locale)
        );
        return "redirect:" + uriBuilder.path("/user/list").build();
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update_form(@PathVariable long id, Model model) {
        UserDto user = userService.findUserById(id);
        model.addAttribute("user", user);
        log.debug("update_form(model={})", model);
        return "user/edit";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {
        log.debug("update(locale={}, user={})", locale, user);
        if (bindingResult.hasErrors()) {
            log.debug("binding errors");
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.debug("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                log.debug("FieldError: {}", fe);
            }
            return (Long.valueOf(user.getId()) == 0)?"user/list":"user/edit";
        }
        if (user.getId() == 0) {            
            userService.createUser(user);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("message.new.user", new Object[]{user.getUsername(), user.getRole()}, locale)
            );
        } else {
            userService.updateUser(user);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("message.update.user", new Object[]{user.getUsername()}, locale)
            );
        }
        return "redirect:" + uriBuilder.path("/user/list").build();
    }
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new UserValidator(userService));
    }
}
