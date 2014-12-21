package pa165.deliveryservice.api;

import java.util.List;
import pa165.deliveryservice.entity.DeliveryStatus;
import pa165.deliveryservice.api.dto.CustomerDto;
import pa165.deliveryservice.api.dto.DeliveryDto;
import pa165.deliveryservice.api.dto.GoodsDto;
import pa165.deliveryservice.api.dto.PostmanDto;

/**
 * Service layer Delivery interface.
 *
 * @author Jan Stastny
 */
public interface DeliveryService {
    
    /**
     * Create delivery and store it into db
     * @param name Name of delivery
     * @param postman 
     * @param goods Goods inside
     * @param customer
     * @param status Status of delivery.
     */
    void createDelivery(String name, PostmanDto postman, List<GoodsDto> goods, CustomerDto customer, DeliveryStatus status);
    
    void createDelivery(DeliveryDto delivery);
    /**
     * Find delivery by given id.
     * @param id id of delivery
     * @return DeliveryDto with given id or null if there doesn't exist
     * @throws IllegalArgumentException
     */
    DeliveryDto findDelivery(long id);
    
    /**
     * Update delivery
     * @param delivery 
     */
    void updateDelivery(DeliveryDto delivery);
    
    /**
     * Delete given delivery if exists in DB.
     * @param delivery 
     */
    void deleteDelivery(DeliveryDto delivery);
    
    /**
     * Return list of all deliveries in DB.
     * @return List of deliveries
     */
    List<DeliveryDto> getAllDeliveries();
}
