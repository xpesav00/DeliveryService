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
 * @author Jan Stastny
 */
public interface DeliveryService {
    
    /**
     * Create delivery and store it into dtb.
     * @param name Name of delivery
     * @param postman 
     * @param goods Goods inside
     * @param customer
     * @param status Status of delivery.
     */
    void createDelivery(String name, Postman postman, List<Goods> goods, Customer customer, DeliveryStatus status);
    
    /**
     * Find delivery by given id.
     * @param id id of deliverz
     * @return delivery with given id or null if there doesnt exists.
     * @throws IllegalArgumentException
     */
    Delivery findDelivery(long id);
    
    /**
     * Updates delivery
     * @param delivery 
     */
    void updateDelivery(Delivery delivery);
    
    /**
     * Delete given delivery if exists in DB.
     * @param delivery 
     */
    void deleteDelivery(Delivery delivery);
    
    /**
     * Returns list of all deliveries in DB.
     * @return List of deliveries
     */
    List<Delivery> getAllDeliveries();
}
