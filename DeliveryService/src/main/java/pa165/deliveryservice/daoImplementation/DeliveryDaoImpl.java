package pa165.deliveryservice.daoImplementation; 

import pa165.deliveryservice.daoInterface.DeliveryDao; 
import pa165.deliveryservice.entity.Delivery; 
import java.util.List; 
import javax.persistence.EntityManager; 
import javax.persistence.PersistenceContext;
 
/** 
 * An implementation of DeliveryDao interface
 * @author Jan Šastný 
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
    public void deleteDelivery(Delivery delivery) { 
//        if(id == null && id < 0) { 
//            throw new IllegalArgumentException("Invalid id."); 
//        } 
        Delivery deliveryDb = entityManager.find(Delivery.class, delivery.getId()); 
        entityManager.remove(deliveryDb); 
    } 

    @Override 
    public Delivery getDelivery(Delivery delivery) { 
//        if(id == null && id < 0) { 
//            throw new IllegalArgumentException("Invalid id (id null or < 0)."); 
//        } 
        Delivery deliveryDb = entityManager.find(Delivery.class, delivery.getId()); 
        entityManager.detach(deliveryDb); 
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
}