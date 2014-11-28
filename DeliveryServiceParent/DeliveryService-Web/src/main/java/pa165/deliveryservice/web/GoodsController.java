/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.deliveryservice.web;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pa165.servicelayer.dto.*;
import pa165.servicelayer.serviceInterface.DeliveryService;
import pa165.servicelayer.serviceInterface.GoodsService;

/**
 * SpringMVC Controller for handling goods.
 *
 * @author Drimal
 */
@Controller
@RequestMapping("/good")
public class GoodsController {
    private final static Logger log = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private DeliveryService deliveryService;
    
    @Autowired
    private GoodsService goodsService;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("goods list()");
        model.addAttribute("delivery", new DeliveryDto());
        return "goods/list";
    }
    
    @RequestMapping(value="/list/id", method=RequestMethod.GET)
    public List<GoodsDto> allDeliveryGoods(@PathVariable long id, Model model){
        log.debug("all goods from Postman{}", id);
        DeliveryDto findDelivery = deliveryService.findDelivery(id);
        return findDelivery.getGoods();
    }
//    
//    @RequestMapping(value="/list", method=RequestMethod.GET)
//    public String list(Model model){
//        model.addAttribute("goods", new GoodsDto());
//        return "goods/list";
//    } 
    
    @RequestMapping(value = "update/id", method = RequestMethod.GET)
    public String update_form(@PathVariable long id, Model model) {
        GoodsDto goods = goodsService.findGood(id);
        model.addAttribute("goods", goods);
        log.debug("update_form(model={})", model);
        return "goods/edit";
    }

    @RequestMapping(value = "delete/id", method = RequestMethod.GET)
    public String delete(@PathVariable long id, Model model) {
        GoodsDto goods = goodsService.findGood(id);
        model.addAttribute("goods", goods);
        goodsService.deleteGoods(goods);
        return "goods/edit";
    }
}
