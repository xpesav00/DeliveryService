package pa165.deliveryservice.daoInterface;

import pa165.deliveryservice.entity.Delivery;
import java.util.List;

public interface DeliveryDao {

    List<Delivery> getAllDeliveries();

    void updateDelivery(Delivery delivery);

    void deleteDelivery(Delivery delivery);

    void addDelivery(Delivery delivery);
    
    Delivery getDelivery(Delivery delivery);

}
