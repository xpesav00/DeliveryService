package pa165.deliveryservice.demo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pa165.deliveryservice.DaoContext;
import pa165.deliveryservice.daoImplementation.PostmanDaoImpl;
import pa165.deliveryservice.daoInterface.PostmanDao;
import pa165.deliveryservice.entity.Customer;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.DeliveryStatus;
import pa165.deliveryservice.entity.Postman;

public class PostmanDaoPatternDemo {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(DaoContext.class);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dsInMem");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Delivery del1 = new Delivery();
        del1.setCustomer(new Customer());
        del1.setName("TESTDEL");
        del1.setStatus(DeliveryStatus.INIT);
        
        
        Postman postman1 = new Postman();
        postman1.setFirstName("Jarin");
        postman1.setLastName("Rukavica2");
        
        del1.setPostman(postman1);
        postman1.addDelivery(del1);

        PostmanDao postmanDao = new PostmanDaoImpl();
        postmanDao.addPostman(postman1);
        printAllPostmen(postmanDao.getAllPostmen());

        em.persist(postman1);
        em.getTransaction().commit();
        em.close();
        dbFetch(emf);
        emf.close();
    }

    private static void dbFetch(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        Postman postmandb = em.createQuery("SELECT p from Postman p", Postman.class).getSingleResult();
        System.out.println(postmandb.getFirstName());
        System.out.println(postmandb.getLastName());
        System.out.println(postmandb.getId());
    }

    public static void printAllPostmen(List<Postman> postmen) {
        System.out.println("******************************");
        System.out.println("        LIST POSTMEN        ");
        for (Postman postman : postmen) {
            System.out.println(postman);
        }
    }
}
