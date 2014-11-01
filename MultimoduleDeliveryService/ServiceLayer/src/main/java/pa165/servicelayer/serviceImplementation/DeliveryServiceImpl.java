/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.servicelayer.serviceImplementation;

import java.util.List;
import pa165.deliveryservice.entity.Customer;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.DeliveryStatus;
import pa165.deliveryservice.entity.Goods;
import pa165.deliveryservice.entity.Postman;
import pa165.servicelayer.serviceInterface.DeliveryService;

/**
 *
 * @author Drimal
 */
public class DeliveryServiceImpl implements DeliveryService{

    @Override
    public void createDelivery(String name, Postman postman, List<Goods> goods, Customer customer, DeliveryStatus status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Delivery getDelivery(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDelivery(Delivery delivery) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteDelivery(Delivery delivery) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
