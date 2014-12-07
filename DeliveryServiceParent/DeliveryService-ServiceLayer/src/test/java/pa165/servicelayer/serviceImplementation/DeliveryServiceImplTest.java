package pa165.servicelayer.serviceImplementation;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.dozer.DozerBeanMapper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import pa165.deliveryservice.daoInterface.DeliveryDao;
import pa165.deliveryservice.entity.Customer;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.DeliveryStatus;
import pa165.deliveryservice.entity.Goods;
import pa165.deliveryservice.entity.Postman;
import pa165.servicelayer.dto.DeliveryDto;
import pa165.servicelayer.serviceInterface.DeliveryService;

/**
 *
 * @author Drimal
 */
@RunWith(MockitoJUnitRunner.class)
public class DeliveryServiceImplTest extends TestCase {

    private DeliveryService deliveryService;
    private DeliveryDto deliveryDto;
    private Delivery delivery;
    private DozerBeanMapper mapper;

    @Mock
    DeliveryDao dao;
    
    @Mock
    Postman postman;
    
    @Mock
    List<Goods> goods;
    
    @Mock
    Customer customer;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        deliveryService = new DeliveryServiceImpl();
        mapper = new DozerBeanMapper();
        goods = new ArrayList<>();
        //sets Mock DeliveryDao to deliveryService field
        ReflectionTestUtils.setField(deliveryService, "deliveryDao", dao);
        ReflectionTestUtils.setField(deliveryService, "mapper", mapper);
        
        delivery = new Delivery();
        delivery.setName("Zasilka");
        delivery.setPostman(postman);
        delivery.setGoods(goods);
        delivery.setCustomer(customer);
        delivery.setStatus(DeliveryStatus.INIT);
        
        deliveryDto = mapper.map(delivery, DeliveryDto.class);

        when(dao.getDelivery(delivery.getId())).thenReturn(delivery);
    }

    @After
    public void tearDown() {
        deliveryService = null;
        deliveryDto = null;
        delivery = null;
        mapper = null;
    }

    @Test
    public void testCreateDelivery() {
        deliveryService.createDelivery(deliveryDto);
        verify(dao).addDelivery(delivery);
    }

    @Test
    public void testFindDelivery() {
        when(dao.getDelivery(deliveryDto.getId())).thenReturn(delivery);
        DeliveryDto delDto = deliveryService.findDelivery(deliveryDto.getId());
        assertEquals(delDto, deliveryDto);
    }

    /**
     * Test of updateDelivery method, of class DeliveryServiceImpl.
     */
    @Test
    public void testUpdateDelivery() {
        deliveryService.updateDelivery(deliveryDto);
        verify(dao).updateDelivery(delivery);
    }

    /**
     * Test of deleteDelivery method, of class DeliveryServiceImpl.
     */
    @Test
    public void testDeleteDelivery() {
        deliveryService.deleteDelivery(deliveryDto);
        verify(dao).deleteDelivery(delivery);
    }

    /**
     * Test of getAllDeliveries method, of class DeliveryServiceImpl.
     */
    @Test
    public void testGetAllDeliveries() {
        deliveryService.getAllDeliveries();
        verify(dao).getAllDeliveries();
    }
}
