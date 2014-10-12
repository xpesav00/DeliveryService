package pa165.deliveryservice.demo;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pa165.deliveryservice.DaoContext;
import pa165.deliveryservice.daoImplementation.DeliveryDaoImpl;
import pa165.deliveryservice.entity.Delivery;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import pa165.deliveryservice.entity.Customer;
import pa165.deliveryservice.entity.DeliveryStatus;

public class DeliveryDaoPatternDemo {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(DaoContext.class);
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit");
        EntityManager entityManager = emf.createEntityManager();
        Delivery delivery0 = new Delivery();
            delivery0.setName(generateCode());
            delivery0.setCustomer(new Customer());
            delivery0.setPackages(null);
            delivery0.setPostman(null);
            delivery0.setStatus(DeliveryStatus.INIT);
        entityManager.getTransaction().begin();
        entityManager.persist(delivery0);
        entityManager.getTransaction().commit();
        entityManager.close();
        dbFetch(emf);
        
        
        DeliveryDaoImpl deliveryDAO = new DeliveryDaoImpl();
        Delivery delivery1 = new Delivery();
            delivery1.setName(generateCode());
            delivery1.setCustomer(new Customer());
            delivery1.setPackages(null);
            delivery1.setPostman(null);
            delivery1.setStatus(DeliveryStatus.INIT);
        
        deliveryDAO.addDelivery(delivery1);
        
        List<Delivery> allDeliveries = deliveryDAO.getAllDeliveries();
        System.out.println("All deliveries:"+allDeliveries);
        deliveryDAO.getDelivery(Long.valueOf(0));
        deliveryDAO.deleteDelivery(Long.valueOf(0));
        delivery1.setName(generateCode());
        deliveryDAO.updateDelivery(delivery1);
        allDeliveries = deliveryDAO.getAllDeliveries();
        System.out.println("All deliveries:"+allDeliveries);
        
    }
    
    private static String generateCode() {
        String code = UUID.randomUUID().toString().substring(0, Delivery.NAME_LENGTH);
        return code;
    }
    
    private static void dbFetch(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        Delivery d = em.createQuery("SELECT d from Delivery d", Delivery.class).getSingleResult();
        System.out.println(d);
        em.close();
    }
}
