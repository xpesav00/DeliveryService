package pa165.deliveryservice.daoImplementation;

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

/**
 * An implementation of DeliveryDao interface
 * @author Jan Šťastný
 */
public class DeliveryDaoImpl implements DeliveryDao{
    
    private EntityManagerFactory entityManagerFactory;
    
//    @PersistenceContext
//    public void setEntityManager(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
    public DeliveryDaoImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("myUnit");
    }
    
    public DeliveryDaoImpl(EntityManagerFactory entityManagerFactory) {
        if(entityManagerFactory == null) throw new NullPointerException("Entity manager factory can not be null.");
        this.entityManagerFactory = entityManagerFactory;
    }
    
    @Override
    public void addDelivery(Delivery delivery) {
        evaluateDelivery(delivery);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(delivery);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    @Override
    public void updateDelivery(Delivery delivery) {
        evaluateDelivery(delivery);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(delivery);              
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    @Override
    public void deleteDelivery(Delivery delivery) {
        evaluateDelivery(delivery);
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Delivery deletedDelivery = entityManager.find(Delivery.class, delivery.getId());
        entityManager.remove(deletedDelivery);              
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    @Override
    public Delivery getDelivery(long id) {
        if(id <= 0) {
            throw new IllegalArgumentException("Invalid id (id <= 0).");
        }
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Delivery delivery = entityManager.find(Delivery.class, id);
        entityManager.detach(delivery);
        entityManager.getTransaction().commit();
        entityManager.close();
        return delivery;
    }
    
    @Override
    public List<Delivery> getAllDeliveries() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Delivery> resultList = entityManager.createQuery("SELECT d from Delivery d",Delivery.class).getResultList();
        entityManager.close();
        return resultList;
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
        if(delivery.getId() < 0) {
            throw new IllegalArgumentException("Invalid id, value <= 0.");
        }
        if(delivery.getName().length() == 0 || delivery.getName() == null || delivery.getName().length() > Delivery.NAME_LENGTH) {
            throw new IllegalArgumentException("Invalid name of delivery.");
        }
    }

}
