package pa165.servicelayer.serviceImplementation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pa165.deliveryservice.api.UserService;
import pa165.deliveryservice.api.dto.UserDto;
import pa165.deliveryservice.daoInterface.UserDao;
import pa165.deliveryservice.entity.UserEntity;
import pa165.deliveryservice.entity.UserRole;

/**
 * Service layer for User entity
 *
 * @author Drimal
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private Mapper mapper;

    @PostConstruct
    public void preloadDB(){
        UserEntity admin = new UserEntity();
        admin.setUsername("admin");
        admin.setUserRole(UserRole.ROLE_ADMIN);

        UserEntity restUser = new UserEntity();
        restUser.setUsername("rest");
        restUser.setUserRole(UserRole.ROLE_REST);

        UserEntity user = new UserEntity();
        user.setUsername("user");
        user.setUserRole(UserRole.ROLE_USER);

        MessageDigest digest = null;
        try {
            digest = java.security.MessageDigest.getInstance("MD5");
            digest.update("admin".getBytes());
            admin.setPassword(digest.digest());

            digest.update("rest".getBytes());
            restUser.setPassword(digest.digest());

            digest.update("user".getBytes());
            user.setPassword(digest.digest());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        userDao.addUser(admin);
        userDao.addUser(restUser);
        userDao.addUser(user);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void createUser(UserDto user) {
        if(user == null){
            throw new NullPointerException("User can't be null.");
        }
        userDao.addUser(mapper.map(user, UserEntity.class));
    }

    @Override
    @Secured("ROLE_ADMIN")
    public boolean deleteUser(UserDto user) {
        if(user == null){
            throw new NullPointerException("User can't be null.");
        }
        userDao.deleteUser(mapper.map(user, UserEntity.class));
        return true;
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void updateUser(UserDto user) {
        if(user == null){
            throw new NullPointerException("User can't be null.");
        }
        userDao.updateUser(mapper.map(user, UserEntity.class));
    }

    @Override
    public UserDto getUserByName(String name) {
        List<UserEntity> retrieveAllUsers = userDao.retrieveAllUsers();
        for (UserEntity user : retrieveAllUsers) {
            if(user.getUsername().equals(name)){
                return mapper.map(user, UserDto.class);
            }
        }
        return null;
    }

    @Override
    public List<UserDto> retrieveAllUsers(){
        List<UserDto> result = new ArrayList<>();
        List<UserEntity> listUserEntity = userDao.retrieveAllUsers();
        for (UserEntity user : listUserEntity) {
            result.add(mapper.map(user, UserDto.class));
        }
        return result;
    }

    @Override
//    @Secured("ROLE_ADMIN")
    public boolean userMatcher(String name, byte[] password) {
        UserDto userDb = getUserByName(name);
        if(userDb != null){
            return Arrays.equals(password, userDb.getPassword());
        }
        return false;
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public UserDto findUserById(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id can't be negative.");
        }
        UserEntity user = userDao.getUser(id);
        return mapper.map(user, UserDto.class);
    }
}
