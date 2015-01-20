package pa165.deliveryservice.daoImplementation;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pa165.deliveryservice.daoInterface.UserDao;
import pa165.deliveryservice.entity.Goods;
import pa165.deliveryservice.entity.User;

/**
 * Implementation of UserDao interface
 * @author Drimal
 */
public class UserDaoImpl implements UserDao{
    private final static Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    public UserDaoImpl(){
        log.info("Create database connection.");
    }

    @Override
    public void updateUser(User user) {
        log.info("Update user " + user + " to database.");
        if (user == null) {
            throw new NullPointerException("User is null.");
        }
        em.merge(user);
    }

    @Override
    public void addUser(User user) {
        log.info("Add user " + user + " to database.");
        if(user == null){
            throw new NullPointerException("user is null.");
        }
        if(user.getUsername().isEmpty()){
            throw new IllegalArgumentException("Name must be set.");
        }
        if(user.getPassword() == null){
            throw new IllegalArgumentException("Password must be set.");
        }
        em.persist(user);
    }

    @Override
    public User getUser(long id) {
        log.info("Get specific user from database.");
        if (id <= 0) {
            throw new IllegalArgumentException("Id is zero or negative.");
        }
        User userDb = null;
        try {
            userDb = em.find(User.class, id);
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("Unknow object.");
        }
        return userDb;
    }

    @Override
    public void deleteUser(User user) {
        log.info("Delete user " + user + " from database.");
        if (user == null) {
            throw new NullPointerException("User is null.");
        }
        User userDb = null;
        try {
            userDb = em.find(User.class, user.getId());
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("Unknown object to delete.");
        }
        em.remove(userDb);
    }

    @Override
    public List<User> retrieveAllUsers() {
        log.info("Retrieve all users");
        List<User> resultList = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        return resultList;
    }
}
