package pa165.deliveryservice.daoImplementation;

import pa165.deliveryservice.daoInterface.PostmanDao;
import pa165.deliveryservice.entity.Postman;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Implementation of PostmanDao.
 *
 * @author Martin Nekula
 */
@Repository
public class PostmanDaoImpl implements PostmanDao {

    private static final Logger log = LoggerFactory.getLogger(PostmanDaoImpl.class);
    @PersistenceContext
    private EntityManager em;

    public PostmanDaoImpl() {
        log.info("Create database connection.");
    }

    public PostmanDaoImpl(EntityManager em) {
        log.info("Create database connection with param.");
        if (em == null) {
            throw new NullPointerException();
        }
        this.em = em;
    }

    @Override
    public Postman getPostman(long id) {
        log.info("Get specific postman from database.");
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid id (zero or negative).");
        }
        Postman postmanDb = null;
        try {
            postmanDb = em.find(Postman.class, id);
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("Unknow postman object.");
        }
        return postmanDb;
    }

    @Override
    public List<Postman> getAllPostmen() {
        log.info("Retrieve all postmen from database.");
        List<Postman> postmen = em.createQuery("SELECT p from Postman p", Postman.class).getResultList();
        return postmen;
    }

    @Override
    public void updatePostman(Postman postman) {
        log.info("Update postman " + postman + " to database.");
        if (postman == null) {
            throw new NullPointerException("Postman is null.");
        }
        em.merge(postman);
    }

    @Override
    public void deletePostman(Postman postman) {
        log.info("Delete postman " + postman + " from database.");
        if (postman == null) {
            throw new NullPointerException("Postman is null.");
        }
        if (postman.getId() <= 0) {
            throw new IllegalArgumentException("Trying to delete postman with no id assigned.");
        }
        Postman postmandb = em.find(Postman.class, postman.getId());
        em.remove(postmandb);
    }

    @Override
    public void addPostman(Postman postman) {
        log.info("Add postman " + postman + " to database.");
        if (postman == null) {
            throw new NullPointerException("Postman is null.");
        }
        if (postman.getId() > 0) {
            throw new IllegalStateException("Trying to add postman with id assigned.");
        }
        em.persist(postman);
    }

    public void closeConnection() {
        if (em != null) {
            em.close();
        }
    }
}
