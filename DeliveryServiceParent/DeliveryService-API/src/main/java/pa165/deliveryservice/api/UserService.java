package pa165.deliveryservice.api;

import pa165.deliveryservice.api.dto.UserDto;

/**
 *
 * @author Drimal
 */
public interface UserService {

    /**
     * Create user
     * @param user
     */
    void createUser(UserDto user);

    /**
     * Delete specific user
     * @param user
     * @return
     */
    boolean deleteUser(UserDto user);

    /**
     * Update specific user
     * @param user
     */
    void updateUser(UserDto user);

    /**
     * Retrieve user by name
     * @param name
     * @return user with specific name
     */
    UserDto getUserByName(String name);

    /**
     * Check if specific user supported
     * @param name
     * @param password
     * @return true if specific user exist
     */
    boolean userMatcher(String name, byte[] password);
}
