package pa165.deliveryservice.daoImplementation;

import java.util.ArrayList;
import pa165.deliveryservice.daoInterface.DeliveryDao;
import pa165.deliveryservice.entity.Delivery;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * An implementation of DeliveryDao interface
 * @author Jan Šťastný
 */
public class DeliveryDaoImpl implements DeliveryDao{
    
    @PersistenceContext
    private EntityManager entityManager;
    
//    public DeliveryDaoImpl() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit");
//        entityManager = emf.createEntityManager();
//    }
    
    @Override
    public void addDelivery(Delivery delivery) {
        evaluateDelivery(delivery);
        entityManager.persist(delivery);
        entityManager.flush();
        entityManager.detach(delivery);
    }
    
    @Override
    public void updateDelivery(Delivery delivery) {
        evaluateDelivery(delivery);
        entityManager.merge(delivery);
        entityManager.flush();        
        entityManager.detach(delivery);
    }
    
    @Override
    public void deleteDelivery(Long id) {
        if(id == null && id < 0) {
            throw new IllegalArgumentException("Invalid id.");
        }
        Delivery delivery = entityManager.find(Delivery.class, id);
        entityManager.remove(delivery);
    }
    
    @Override
    public Delivery getDelivery(Long id) {
        if(id == null && id < 0) {
            throw new IllegalArgumentException("Invalid id (id null or < 0).");
        }
        Delivery delivery = entityManager.find(Delivery.class, id);
        entityManager.detach(delivery);
        return delivery;
    }
    
    @Override
    public List<Delivery> getAllDeliveries() {
        return entityManager.createQuery("SELECT d from Delivery d",Delivery.class).getResultList();
    }
    
    /**
     * Evaluates given delivery.
     * @param delivery
     * @throws IllegalArgumentException 
     */
    private void evaluateDelivery(Delivery delivery) throws IllegalArgumentException {
        if(delivery == null) {
            throw new IllegalArgumentException("Delivery can not be null!");
        }
        if(delivery.getId() != null && delivery.getId() < 0) {
            throw new IllegalArgumentException("Invalid id, value < 0.");
        }
        if(delivery.getName().length() == 0 || delivery.getName() == null || delivery.getName().length() > Delivery.NAME_LENGTH) {
            throw new IllegalArgumentException("Invalid name of delivery.");
        }
    }

    @Override
    public void addDelivery() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
