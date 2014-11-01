/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.servicelayer.serviceInterface;

import java.util.List;
import pa165.deliveryservice.entity.Customer;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.DeliveryStatus;
import pa165.deliveryservice.entity.Goods;
import pa165.deliveryservice.entity.Postman;

/**
 *
 * @author Drimal
 */
public interface DeliveryService {
    
    void createDelivery(String name, Postman postman, List<Goods> goods, Customer customer, DeliveryStatus status);
    
    
    Delivery getDelivery(long id);
    
    
    void updateDelivery(Delivery delivery);
    
    
    void deleteDelivery(Delivery delivery);
    
    
    List<Delivery> getAllDeliveries();
}
