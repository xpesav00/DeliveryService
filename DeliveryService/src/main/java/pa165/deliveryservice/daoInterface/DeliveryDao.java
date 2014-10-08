package pa165.deliveryservice.daoInterface;

import pa165.deliveryservice.entity.Delivery;
import java.util.List;

public interface DeliveryDao {

    List<Delivery> getAllDelivery();

    void updateDelivery(Delivery delivery);

    void deleteDelivery(Delivery delivery);

    void addDelivery();

}
