package daoInterface;

import entity.Delivery;
import java.util.List;

public interface DeliveryDao {

    List<Delivery> getAllDelivery();

    void updateDelivery();

    void deleteDelivery();

    void addDelivery();

}
