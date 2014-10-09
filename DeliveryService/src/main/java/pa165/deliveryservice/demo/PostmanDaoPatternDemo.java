package pa165.deliveryservice.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pa165.deliveryservice.DaoContext;
import pa165.deliveryservice.entity.Postman;

public class PostmanDaoPatternDemo {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(DaoContext.class);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Postman postman1 = new Postman();
        postman1.setFirstName("Jarin");
        postman1.setLastName("Rukavica2");
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
        em.close();
    }
}
