package pa165.servicelayer.serviceImplementation;

import org.dozer.DozerBeanMapper;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.test.util.ReflectionTestUtils;
import pa165.deliveryservice.api.UserService;
import pa165.deliveryservice.api.dto.UserDto;
import pa165.deliveryservice.daoInterface.UserDao;
import pa165.deliveryservice.entity.User;

/**
 *
 * @author Drimal
 */
public class UserServiceImplTest extends AbstractIntegrationTest{
    private UserService userService;
    private UserDto userDto;
    private User user;
    private DozerBeanMapper mapper;

    @Mock
    UserDao dao;

    @Before
    public void setUp(){
        userService = new UserServiceImpl();
        mapper = new DozerBeanMapper();
        ReflectionTestUtils.setField(userService, "userDao", dao);
        ReflectionTestUtils.setField(userService, "mapper", mapper);

        user = new User();
        user.setUsername("anton");
        user.setPassword(new byte[]{1,2,3});
        user.setId(1);
        userDto = mapper.map(user, UserDto.class);

        when(dao.getUser(user.getId())).thenReturn(user);
    }

    @After
    public void tearDown(){
        userService = null;
        userDto = null;
        user = null;
        mapper = null;
    }

    @Test
    public void testAddUser(){
        userService.createUser(userDto);
        verify(dao).addUser(user);
    }

    @Test
    public void testDeleteUser(){
        userService.deleteUser(userDto);
        verify(dao).deleteUser(user);
    }

    @Test
    public void testUpadeUser(){
        userService.updateUser(userDto);
        verify(dao).updateUser(user);
    }

    @Test
    public void testMatcherUser(){
        boolean userMatcher = userService.userMatcher("anton", new byte[]{1,2,3});
        assertTrue(userMatcher, "Users should be equals");
    }
}
