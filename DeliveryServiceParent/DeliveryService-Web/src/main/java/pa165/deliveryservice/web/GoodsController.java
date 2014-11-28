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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import pa165.servicelayer.dto.*;
import pa165.servicelayer.serviceInterface.DeliveryService;
import pa165.servicelayer.serviceInterface.GoodsService;

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
    
    private DeliveryDto selectedDelivery = null;
    
    //volani list.jsp s hodnotou id
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
        model.addAttribute("deliveries", deliveryService.getAllDeliveries());
        log.debug("update_form(model={})", model);
        return "goods/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model) {
        GoodsDto goods = goodsService.findGood(id);
        model.addAttribute("goods", goods);
        goodsService.deleteGoods(goods);
        return "/goods/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute GoodsDto goods, BindingResult bindingResult, 
            RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {
        log.debug("update(locale={}, goods={})", locale, goods);
        if(goods.getId() == 0){
            log.debug("null delivery : {}", selectedDelivery == null);
            goodsService.createGoods(goods.getPrice(), goods.getSeller(), selectedDelivery);
        } else {
            goods.setDelivery(selectedDelivery);
            goodsService.updateGoods(goods);
        }
        
        return "redirect:" + uriBuilder.path("/goods/list/"+ selectedDelivery.getId()).build();
    }
}
