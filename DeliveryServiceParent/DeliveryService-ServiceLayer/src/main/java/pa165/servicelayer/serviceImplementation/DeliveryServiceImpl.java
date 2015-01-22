package pa165.servicelayer.serviceImplementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pa165.deliveryservice.daoInterface.DeliveryDao;
import pa165.deliveryservice.entity.*;
import pa165.deliveryservice.api.dto.AddressDto;
import pa165.deliveryservice.api.dto.CustomerDto;
import pa165.deliveryservice.api.dto.DeliveryDto;
import pa165.deliveryservice.api.dto.GoodsDto;
import pa165.deliveryservice.api.dto.PostmanDto;
import pa165.deliveryservice.api.DeliveryService;

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

        CustomerDto customer = new CustomerDto();
        AddressDto a = new AddressDto();
        a.setCity("Božkov");
        a.setStreet("Rumová");
        a.setPostcode(66677);
        customer.setAddress(a);
        customer.setFirstName("Honza");
        customer.setLastName("Pelda");

        PostmanDto postman = new PostmanDto();
        postman.setFirstName("Karel");
        postman.setLastName("Pepik");

        GoodsDto goods = new GoodsDto();
        goods.setPrice(100);
        goods.setSeller("Sony");
        createDelivery("CZC.cz", postman, Arrays.asList(goods), customer, DeliveryStatus.INIT);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void createDelivery(String name, PostmanDto postman, List<GoodsDto> goods, CustomerDto customer, DeliveryStatus status) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name of delivery can not be empty.");
        }else if(customer == null) {
            throw new IllegalArgumentException("Customer can not be null.");
        }
        if(status != DeliveryStatus.INIT) {
            throw new IllegalArgumentException("Invalid status("+status+"), delivery status should be INIT.");
        }

        DeliveryDto deliveryDto = new DeliveryDto();
        deliveryDto.setName(name);
        deliveryDto.setPostman(postman);
        deliveryDto.setGoods(goods);
        deliveryDto.setCustomer(customer);
        deliveryDto.setStatus(status);
        Delivery delivery = mapper.map(deliveryDto, Delivery.class);

        try{
            deliveryDao.addDelivery(delivery);
        }catch(Exception e){
            throw new DataAccessException("Error in persistence layer.", e){};
        }
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void createDelivery(DeliveryDto deliveryDto){
        Delivery delivery = mapper.map(deliveryDto, Delivery.class);

        try{
            deliveryDao.addDelivery(delivery);
        }catch(Exception e){
            throw new DataAccessException("Error in persistence layer.", e){};
        }
    }

    @Override
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public DeliveryDto findDelivery(long id) {
        if(id < 0) {
            throw new IllegalArgumentException("Id can not be less than 0.");
        }
        Delivery delivery = null;
        delivery = deliveryDao.getDelivery(id);
        return mapper.map(delivery, DeliveryDto.class);
    }

    @Override
    @Secured("ROLE_ADMIN")
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
    @Secured("ROLE_ADMIN")
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
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<DeliveryDto> getAllDeliveries() {
        List<DeliveryDto> allDeliveries = new ArrayList<>();
        List<Delivery> allDeliveryDaos = deliveryDao.getAllDeliveries();
        for(Delivery delivery : allDeliveryDaos) {
            allDeliveries.add(mapper.map(delivery, DeliveryDto.class));
        }
        return allDeliveries;
    }
}
