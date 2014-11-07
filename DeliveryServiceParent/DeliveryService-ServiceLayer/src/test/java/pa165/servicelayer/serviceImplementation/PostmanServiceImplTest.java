package pa165.servicelayer.serviceImplementation;

import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import pa165.deliveryservice.daoInterface.PostmanDao;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.Postman;
import pa165.servicelayer.dto.PostmanDto;
import pa165.servicelayer.serviceInterface.PostmanService;

/**
 *
 * @author Martin Nekula
 */
@RunWith(MockitoJUnitRunner.class)
public class PostmanServiceImplTest extends AbstractIntegrationTest {
    
    private PostmanService postmanService;
    private PostmanDto postmanDto;
    private Postman postman;
    private DozerBeanMapper mapper;
    
    @Mock
    PostmanDao dao;
    
    public PostmanServiceImplTest() {
    }
    
    @Before
    public void setUp() {
        postmanService = new PostmanServiceImpl();
        mapper = new DozerBeanMapper();
        //sets Mock PostmanDao to postmanService field
        ReflectionTestUtils.setField(postmanService, "postmanDao", dao);
        ReflectionTestUtils.setField(postmanService, "mapper", mapper);
        
        postman = getTestPostmanInstance("");
        postman.setId(1);
        postmanDto = mapper.map(postman, PostmanDto.class);
        
        when(dao.getPostman(postman.getId())).thenReturn(postman);
    }
    
    @After
    public void tearDown() {
    postmanService = null;
    postmanDto = null;
    postman = null;
    mapper = null;
    }
    
    @Test
    public void testAddPostman() {
        postmanService.addPostman(postmanDto);
        verify(dao).addPostman(postman);
    }
    
    
    public static Postman getTestPostmanInstance(String suffix) {
        String firstName = "Milan"+suffix;
        String lastName = "Nedoma"+suffix;
        List<Delivery> deliveries = new ArrayList<>();
        
        Postman postman = new Postman();
        postman.setFirstName(firstName);
        postman.setLastName(lastName);
        postman.setDeliveries(deliveries);
        
        return postman;
    }
}
