package pa165.servicelayer.serviceImplementation;

import java.util.Arrays;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pa165.deliveryservice.api.UserService;
import pa165.deliveryservice.api.dto.UserDto;
import pa165.deliveryservice.daoInterface.UserDao;
import pa165.deliveryservice.entity.User;

/**
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

    @Override
    public void createUser(UserDto user) {
        if(user == null){
            throw new NullPointerException("User can't be null.");
        }
        userDao.addUser(mapper.map(user, User.class));
    }

    @Override
    public boolean deleteUser(UserDto user) {
        if(user == null){
            throw new NullPointerException("User can't be null.");
        }
        userDao.deleteUser(mapper.map(user, User.class));
        return true;
    }

    @Override
    public void updateUser(UserDto user) {
        if(user == null){
            throw new NullPointerException("User can't be null.");
        }
        userDao.updateUser(mapper.map(user, User.class));
    }

    @Override
    public UserDto getUserByName(String name) {
        List<User> retrieveAllUsers = userDao.retrieveAllUsers();
        for (User user : retrieveAllUsers) {
            if(user.getUsername().equals(name)){
                return mapper.map(user, UserDto.class);
            }
        }
        return null;
    }

    @Override
    public boolean userMatcher(String name, byte[] password) {
        UserDto userDb = getUserByName(name);
        if(userDb != null){
            return Arrays.equals(password, userDb.getPassword());
        }
        return false;
    }
}
