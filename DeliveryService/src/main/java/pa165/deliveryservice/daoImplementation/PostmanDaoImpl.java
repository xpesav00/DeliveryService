package pa165.deliveryservice.daoImplementation;

import pa165.deliveryservice.daoInterface.PostmanDao;
import pa165.deliveryservice.entity.Postman;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PostmanDaoImpl implements PostmanDao {

    private EntityManagerFactory emf;

    public PostmanDaoImpl() {
        emf = Persistence.createEntityManagerFactory("myUnit");
    }

    public PostmanDaoImpl(EntityManagerFactory emf) {
        if (emf == null) {
            throw new NullPointerException();
        }
        this.emf = emf;
    }

    @Override
    public List<Postman> getAllPostmen() {
        EntityManager em = emf.createEntityManager();
        List<Postman> postmen = em.createQuery("SELECT p from Postman p", Postman.class).getResultList();
        em.close();
        return postmen;
    }

    @Override
    public void updatePostman(Postman postman) {
        if (postman == null) {
            throw new NullPointerException("Postman is null.");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(postman);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deletePostman(Postman postman) {
        if (postman == null) {
            throw new NullPointerException("Postman is null.");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Postman postmandb = em.find(Postman.class, postman.getId());
        em.remove(postmandb);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void addPostman(Postman postman) {
        if (postman == null) {
            throw new NullPointerException("Postman is null.");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(postman);
        em.getTransaction().commit();
        em.close();
    }

}
