package pa165.deliveryservice.daoImplementation;

import pa165.deliveryservice.daoInterface.PostmanDao;
import pa165.deliveryservice.entity.Postman;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PostmanDaoImpl implements PostmanDao {

    private EntityManagerFactory emf;

    public PostmanDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Postman> getAllPostmen() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Postman> postmen = em.createQuery("SELECT p from Postman p", Postman.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return postmen;
    }

    @Override
    public void updatePostman(Postman postman) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Postman postmandb = em.find(Postman.class, postman.getId());
        postmandb.setFirstName(postman.getFirstName());
        postmandb.setLastName(postman.getLastName());
        postmandb.setDeliveries(postman.getDeliveries());
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deletePostman(Postman postman) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Postman postmandb = em.find(Postman.class, postman.getId());
        em.remove(postmandb);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void addPostman(Postman postman) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(postman);
        em.getTransaction().commit();
        em.close();
    }

}
