package pa165.deliveryservice.web;

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
import pa165.deliveryservice.api.DeliveryService;
import pa165.deliveryservice.api.GoodsService;
import pa165.deliveryservice.api.dto.DeliveryDto;
import pa165.deliveryservice.api.dto.GoodsDto;
import pa165.deliveryservice.validation.GoodsValidator;

/**
 * SpringMVC Controller for handling goods.
 *
 * @author Drimal
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    private final static Logger log = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private DeliveryService deliveryService;
    
    @Autowired
    private GoodsService goodsService;
    
    @Autowired
    private MessageSource messageSource;
    
    private DeliveryDto selectedDelivery = null;

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public String listId(@PathVariable long id, Model model) {
        log.debug("goods list()");
        model.addAttribute("goods", new GoodsDto());
        model.addAttribute("deliveryId", id);
        DeliveryDto findDelivery = deliveryService.findDelivery(id);
        selectedDelivery = findDelivery;
        model.addAttribute("delgoods", findDelivery.getGoods());
        return "/goods/list";
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update_form(@PathVariable long id, Model model) {
        GoodsDto goods = goodsService.findGood(id);
        model.addAttribute("goods", goods);
        model.addAttribute("deliveryId", selectedDelivery.getId());
        log.debug("update_form(model={})", model);
        return "goods/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes, Model model, UriComponentsBuilder uriBuilder, Locale locale) {
        GoodsDto goods = goodsService.findGood(id);
        model.addAttribute("goods", goods);
        goodsService.deleteGoods(goods);

        DeliveryDto findDelivery = deliveryService.findDelivery(selectedDelivery.getId());
        selectedDelivery = findDelivery;
        model.addAttribute("delgoods", findDelivery.getGoods());
        
        redirectAttributes.addFlashAttribute(
                "message",
                messageSource.getMessage("message.delete.goods", new Object[]{goods.getSeller()}, locale)
        );
        return "redirect:" + uriBuilder.path("/goods/list/"+ selectedDelivery.getId()).build();
    }

    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new GoodsValidator());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("goods") GoodsDto goods, BindingResult bindingResult, Model model,
            RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {
        log.debug("update(locale={}, goods={})", locale, goods);
        if (bindingResult.hasErrors()) {
            log.debug("binding errors");
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.debug("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                log.debug("FieldError: {}", fe);
            }
            model.addAttribute("goods", goods);
            model.addAttribute("deliveryId", selectedDelivery.getId());
            model.addAttribute("delgoods", selectedDelivery.getGoods());
            return (goods.getId() == 0)?"goods/list":"goods/edit";
        }
        if(goods.getId() == 0){
            log.debug("null delivery : {}", selectedDelivery == null);
            goodsService.createGoods(goods.getPrice(), goods.getSeller(), selectedDelivery);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("message.new.goods", new Object[]{goods.getSeller(), goods.getPrice(),selectedDelivery.getName()}, locale)
            );
        } else {
            goods.setDelivery(selectedDelivery);
            goodsService.updateGoods(goods);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("message.update.goods", new Object[]{goods.getSeller(), goods.getPrice()}, locale)
            );
        }
        
        return "redirect:" + uriBuilder.path("/goods/list/"+ selectedDelivery.getId()).build();
    }
}
