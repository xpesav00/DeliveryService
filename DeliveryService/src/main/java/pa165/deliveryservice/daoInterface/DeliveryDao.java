package pa165.deliveryservice.daoInterface;

import pa165.deliveryservice.entity.Delivery;
import java.util.List;
/**
 * Delivery DAO interface
 * @author Jan Šťastný
 */
public interface DeliveryDao {
    /**
     * Stores new delivery
     * @param delivery
     * @throws IllegalArgumentException if delivery is null || delivery's id is null or less or equal to 0 || name is null || name.length() not in &lt;1,13&gt;
     */
    void addDelivery(Delivery delivery);
    
    /**
     * Finds delivery by id
     * @param id
     * @return Appropriate delivery
     * @throws IllegalArgumentException if delivery's id is null or less or equal to 0
     */
    Delivery getDelivery(long id);
    
    /**
     * Updates delivery
     * @param delivery
     * @throws IllegalArgumentException if delivery is null || delivery's id is null or less or equal to 0 || name is null || name.length() not in &lt;1,13&gt;
     */
    void updateDelivery(Delivery delivery);
    
    /**
     * Deletes delivery founded by given id.
     * @param delivery 
     */
    void deleteDelivery(Delivery delivery);
    
    /**
     * Returns all deliveries in database
     * @return List<Delivery> all deliveries in database
     */
    List<Delivery> getAllDeliveries();
    /**
     * Close opened connection
     */
    void closeConnection();
}
