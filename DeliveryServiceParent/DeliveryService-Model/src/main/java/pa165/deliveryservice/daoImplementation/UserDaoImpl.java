package pa165.deliveryservice.daoImplementation;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pa165.deliveryservice.daoInterface.UserDao;
import pa165.deliveryservice.entity.UserEntity;

/**
 * Implementation of UserDao interface
 * @author Drimal
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao{
    private final static Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    public UserDaoImpl(){
        log.info("Create database connection.");
    }

    @Override
    public void updateUser(UserEntity user) {
        log.info("Update user " + user + " to database.");
        if (user == null) {
            throw new NullPointerException("User is null.");
        }
        em.merge(user);
    }

    @Override
    public void addUser(UserEntity user) {
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
    public UserEntity getUser(long id) {
        log.info("Get specific user from database.");
        if (id <= 0) {
            throw new IllegalArgumentException("Id is zero or negative.");
        }
        UserEntity userDb = null;
        try {
            userDb = em.find(UserEntity.class, id);
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("Unknow object.");
        }
        return userDb;
    }

    @Override
    public void deleteUser(UserEntity user) {
        log.info("Delete user " + user + " from database.");
        if (user == null) {
            throw new NullPointerException("User is null.");
        }
        UserEntity userDb = null;
        try {
            userDb = em.find(UserEntity.class, user.getId());
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("Unknown object to delete.");
        }
        em.remove(userDb);
    }

    @Override
    public List<UserEntity> retrieveAllUsers() {
        log.info("Retrieve all users");
        List<UserEntity> resultList = em.createQuery("SELECT u FROM UserEntity u", UserEntity.class).getResultList();
        return resultList;
    }
}
