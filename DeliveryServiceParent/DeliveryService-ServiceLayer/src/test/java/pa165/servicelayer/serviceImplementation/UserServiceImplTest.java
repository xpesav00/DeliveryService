package pa165.servicelayer.serviceImplementation;

import java.util.Arrays;
import org.dozer.DozerBeanMapper;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import pa165.deliveryservice.api.UserService;
import pa165.deliveryservice.api.dto.UserDto;
import pa165.deliveryservice.daoInterface.UserDao;
import pa165.deliveryservice.entity.UserEntity;

/**
 *
 * @author Drimal
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest extends AbstractIntegrationTest {

    private UserService userService;
    private UserDto userDto;
    private UserDto userDto2;
    private UserEntity user;
    private UserEntity user2;
    private DozerBeanMapper mapper;

    @Mock
    UserDao dao;

    @Before
    public void setUp() {
        userService = new UserServiceImpl();
        mapper = new DozerBeanMapper();
        ReflectionTestUtils.setField(userService, "userDao", dao);
        ReflectionTestUtils.setField(userService, "mapper", mapper);

        user = new UserEntity();
        user.setUsername("anton");
        user.setPassword(new byte[]{1, 2, 3});
        user.setId(1);
        user2 = new UserEntity();
        user2.setUsername("notna");
        user2.setPassword(new byte[]{1, 2, 3, 4});
        user2.setId(2);
      
        userDto = mapper.map(user, UserDto.class);
        userDto2 = mapper.map(user2, UserDto.class);
        userService.createUser(userDto2);

        when(dao.getUser(user.getId())).thenReturn(user);
        when(dao.getUser(user2.getId())).thenReturn(user2);
        when(dao.retrieveAllUsers()).thenReturn(Arrays.asList(user2));
    }

    @After
    public void tearDown() {
        userService = null;
        userDto = null;
        user = null;
        mapper = null;
    }

    @Test
    public void testAddUser() {
        userService.createUser(userDto);
        verify(dao).addUser(user);
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(userDto);
        verify(dao).deleteUser(user);
    }

    @Test
    public void testUpadeUser() {
        userService.updateUser(userDto);
        verify(dao).updateUser(user);
    }

    @Test
    public void testMatcherUser() {
        boolean userMatcher = userService.userMatcher("notna", new byte[]{1, 2, 3, 4});
        assertTrue(userMatcher, "Users should be equals");
    }
}
