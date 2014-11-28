package pa165.servicelayer.serviceImplementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pa165.deliveryservice.daoInterface.DeliveryDao;
import pa165.deliveryservice.entity.*;
import pa165.servicelayer.dto.DeliveryDto;
import pa165.servicelayer.serviceInterface.DeliveryService;

/**
 *
 * @author Drimal
 */
@Service("deliveryService")
@Transactional
public class DeliveryServiceImpl implements DeliveryService{
    @Autowired
    private DeliveryDao deliveryDao;
    @Autowired
    private Mapper mapper;
    
    @PostConstruct
    public void preloadDB(){
        System.out.println("Preload DB");

        Customer customer = new Customer();
        customer.setAddress(new Address());
        customer.setFirstName("Honza");
        customer.setLastName("Pelda");
        
        Postman postman = new Postman();
        postman.setFirstName("Karel");
        postman.setLastName("Pepik");
        createDelivery("CZC.cz", postman, Collections.EMPTY_LIST, customer, DeliveryStatus.INIT);
    }
    
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
    public void createDelivery(DeliveryDto deliveryDto){
        Delivery delivery = mapper.map(deliveryDto, Delivery.class);
        
        try{
            deliveryDao.addDelivery(delivery);
        }catch(Exception e){
            throw new DataAccessException("Error in persistence layer.", e){};
        }
    }

    @Override
    public DeliveryDto findDelivery(long id) {
        if(id < 0) {
            throw new IllegalArgumentException("Id can not be less than 0.");
        }
        Delivery delivery = null;
        delivery = deliveryDao.getDelivery(id);
        return mapper.map(delivery, DeliveryDto.class);
    }

    @Override
    public void updateDelivery(DeliveryDto deliveryDto) {
        if(deliveryDto == null){
            throw new NullPointerException("Updated delivery can not be null.");
        }
        Delivery delivery = mapper.map(deliveryDto, Delivery.class);
        try{
            deliveryDao.updateDelivery(delivery);
        }catch(Exception e){
            throw new DataAccessException("Error in persistance layer.", e){};
        }
    }

    @Override
    public void deleteDelivery(DeliveryDto deliveryDto) {
        if(deliveryDto == null) throw new IllegalArgumentException("Can not delete null.");
        Delivery delivery = mapper.map(deliveryDto, Delivery.class);
        try{
            deliveryDao.deleteDelivery(delivery);
        }catch(Exception e){
            throw new DataAccessException("Error in persistance layer.", e){};
        }
    }

    @Override
    public List<DeliveryDto> getAllDeliveries() {
        List<DeliveryDto> allDeliveries = new ArrayList<>();
        List<Delivery> allDeliveryDaos = deliveryDao.getAllDeliveries();
        for(Delivery delivery : allDeliveryDaos) {
            allDeliveries.add(mapper.map(delivery, DeliveryDto.class));
        }
        return allDeliveries;
    }
    
}
