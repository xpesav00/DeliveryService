package pa165.deliveryservice.daoInterface;

import java.util.List;
import pa165.deliveryservice.entity.User;

/**
 * Performs necessary operations with user entities
 *
 * @author Drimal
 */
public interface UserDao {

    /**
     * Update information about a single user
     * @param user user to update
     */
    void updateUser(User user);

    /**
     * Add specific user into DB
     * @param user user to add
     */
    void addUser(User user);

    /**
     * Retrieve specific user by name
     * @param id user's id
     * @return return user
     */
    User getUser(long id);

    /**
     * Delete specific user
     * @param user user to deleteUser
     */
    void deleteUser(User user);

    /**
     * Retrieve all users in db
     * @return
     */
    List<User> retrieveAllUsers();
}
