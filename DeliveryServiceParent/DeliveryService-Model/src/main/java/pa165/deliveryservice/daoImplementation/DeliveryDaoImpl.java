package pa165.deliveryservice.daoImplementation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import pa165.deliveryservice.daoInterface.DeliveryDao;
import pa165.deliveryservice.entity.Delivery;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * An implementation of DeliveryDao interface
 * @author Jan Šťastný
 * @version 02.11.2014
 */
@Repository
@Transactional
public class DeliveryDaoImpl implements DeliveryDao{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DeliveryDaoImpl.class);
    @PersistenceContext
    private EntityManager em;
    
    public DeliveryDaoImpl() {
        log.info("Creating database connection.");
    }
    
    public DeliveryDaoImpl(EntityManager em) {
        log.info("Creating database connection.");
        if(em == null) throw new NullPointerException("Entity manager can not be null.");
        this.em = em;
    }
    
    @Override
    public void addDelivery(Delivery delivery) {
        log.info("Adding new delivery("+delivery+") into DB.");
        evaluateDelivery(delivery);
        em.persist(delivery);
    }
    
    @Override
    public void updateDelivery(Delivery delivery) {
        log.info("Updating delivery("+delivery+")");
        evaluateDelivery(delivery);
        em.merge(delivery);       
    }
    
    @Override
    public void deleteDelivery(Delivery delivery) {
        log.info("Deleting delivery("+delivery+")");
        evaluateDelivery(delivery);
        Delivery foundedDelivery = null;
        try{
            foundedDelivery = em.find(Delivery.class, delivery.getId());
        }catch(NullPointerException e) {
            throw new IllegalArgumentException("Deleting delivery isn't in DB.");
        }
        em.remove(foundedDelivery);
    }
    
    @Override
    public Delivery getDelivery(long id) {
        log.info("Getting delivery with id "+id);
        if(id <= 0) {
            throw new IllegalArgumentException("Invalid id (id <= 0).");
        }
        Delivery foundedDelivery = null;
        try{
            foundedDelivery = em.find(Delivery.class, id);
        }catch(NullPointerException e) {
            throw new IllegalArgumentException("Delivery with id:"+id+" isn't in DB.");
        }
        return foundedDelivery;
    }
    
    @Override
    public List<Delivery> getAllDeliveries() {
        log.info("Getting all deliveries from DB.");
        List<Delivery> resultList = em.createQuery("SELECT d from Delivery d",Delivery.class).getResultList();
        return resultList;
    }
    
    /**
     * Evaluates given delivery.
     * @param delivery
     * @throws IllegalArgumentException 
     */
     private void evaluateDelivery(Delivery delivery) throws IllegalArgumentException {
        if(delivery == null) {
            throw new NullPointerException("Delivery can not be null!");
        }
        if(delivery.getId() < 0) {
            throw new IllegalArgumentException("Invalid id, value < 0.");
        }
        if(delivery.getName().length() == 0 || delivery.getName() == null || delivery.getName().length() > Delivery.NAME_LENGTH) {
            throw new IllegalArgumentException("Invalid name of delivery.");
        }
    }
    
    @Override
    public void closeConnection() {
        if(em != null) em.close();
    }

}
