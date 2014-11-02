/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.servicelayer.serviceImplementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import pa165.deliveryservice.daoImplementation.DeliveryDaoImpl;
import pa165.deliveryservice.entity.*;
import pa165.servicelayer.serviceInterface.DeliveryService;

/**
 *
 * @author Drimal
 */
public class DeliveryServiceImpl implements DeliveryService{
    @Autowired
    private DeliveryDaoImpl deliveryDao;
    
    @Override
    public void createDelivery(String name, Postman postman, List<Goods> goods, Customer customer, DeliveryStatus status) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name of delivery can not be empty.");
        }else if(customer == null) {
            throw new IllegalArgumentException("Customer can not be null.");
        }
        if(status != DeliveryStatus.INIT) {
            throw new IllegalArgumentException("Invalid status("+status+"), delivery status should be INIT.");
        }
        
        Delivery delivery = new Delivery();
        delivery.setName(name);
        delivery.setPostman(postman);
        delivery.setGoods(goods);
        delivery.setCustomer(customer);
        delivery.setStatus(status);
        
        try{
            deliveryDao.addDelivery(delivery);
        }catch(Exception e){
            throw new DataAccessException("Error in persistence layer.", e){};
        }
    }

    @Override
    public Delivery findDelivery(long id) {
        if(id < 0) {
            throw new IllegalArgumentException("Id can not be less than 0.");
        }
        Delivery delivery = null;
        try{
            delivery = deliveryDao.getDelivery(id);
        }catch(Exception e) {
            throw new DataAccessException("Error in persistance layer.", e) {};
        }
        return delivery;
    }

    @Override
    public void updateDelivery(Delivery delivery) {
        if(delivery == null){
            throw new NullPointerException("Updated delivery can not be null.");
        }
        try{
            deliveryDao.updateDelivery(delivery);
        }catch(Exception e){
            throw new DataAccessException("Error in persistance layer.", e){};
        }
    }

    @Override
    public void deleteDelivery(Delivery delivery) {
        if(delivery == null) throw new IllegalArgumentException("Can not delete null.");
        
        try{
            deliveryDao.deleteDelivery(delivery);
        }catch(Exception e){
            throw new DataAccessException("Error in persistance layer.", e){};
        }
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryDao.getAllDeliveries();
    }
    
}
