package pa165.servicelayer.serviceImplementation;

import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertTrue;
import pa165.deliveryservice.daoImplementation.DeliveryDaoImpl;
import pa165.deliveryservice.daoInterface.DeliveryDao;
import pa165.deliveryservice.entity.Customer;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.DeliveryStatus;
import pa165.deliveryservice.entity.Goods;
import pa165.deliveryservice.entity.Postman;
import pa165.servicelayer.serviceInterface.DeliveryService;

/**
 *
 * @author Drimal
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DeliveryServiceImplTest {

    @Mock
    private Postman postman;

    @Mock
    private Postman updatedPostman;

    @Mock
    private Customer customer;

    @Mock
    private Customer updatedCustomer;

    @Mock
    private List<Goods> goods;

    @Mock
    private List<Goods> updatedGoods;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Delivery deliveryOne;

    private Delivery deliveryTwo;

    @Autowired
    DeliveryService service;

    private DeliveryDao deliveryDao;

    @Before
    public void setUp() {
        deliveryOne = new Delivery();
        deliveryOne.setName("Rodinna zasilka");
        deliveryOne.setPostman(postman);
        deliveryOne.setGoods(goods);
        deliveryOne.setCustomer(customer);
        deliveryOne.setStatus(DeliveryStatus.INIT);

        deliveryTwo = new Delivery();
        deliveryTwo.setName("Zasilka pro Prahu");
        deliveryTwo.setPostman(postman);
        deliveryTwo.setGoods(goods);
        deliveryTwo.setCustomer(customer);
        deliveryTwo.setStatus(DeliveryStatus.INIT);

        service = new DeliveryServiceImpl();
        deliveryDao = new DeliveryDaoImpl();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateDelivery() {
        service.createDelivery("Obchodni zasilka", postman, goods, customer, DeliveryStatus.INIT);
        List<Delivery> deliveries = deliveryDao.getAllDeliveries();

        assertEquals(deliveries.size(), 1, "Database should contain one delivery.");
        Delivery delivery = deliveries.get(0);
        assertEquals(delivery.getName(), "Obchodni zasilka");
        assertEquals(delivery.getPostman(), postman);
        assertEquals(delivery.getGoods(), goods);
        assertEquals(delivery.getCustomer(), customer);
        assertEquals(delivery.getStatus(), DeliveryStatus.INIT);
    }

    @Test
    public void testCreateDeliveryWithEmptyName() {
        service.createDelivery("", postman, goods, customer, DeliveryStatus.INIT);
        List<Delivery> deliveries = deliveryDao.getAllDeliveries();
    }

    @Test
    public void testCreateDeliveryWithNullPostman() {
        service.createDelivery("Obchodni zasilka", null, goods, customer, DeliveryStatus.INIT);
        List<Delivery> deliveries = deliveryDao.getAllDeliveries();
    }

    @Test
    public void testCreateDeliveryWithNullGoods() {
        service.createDelivery("Obchodni zasilka", postman, null, customer, DeliveryStatus.INIT);
        List<Delivery> deliveries = deliveryDao.getAllDeliveries();
    }

    @Test
    public void testCreateDeliveryWithEmptyGoods() {
        service.createDelivery("Obchodni zasilka", postman, Collections.<Goods>emptyList(), customer, DeliveryStatus.INIT);
        List<Delivery> deliveries = deliveryDao.getAllDeliveries();
    }

    @Test
    public void testCreateDeliveryWithNullCustomer() {
        service.createDelivery("Obchodni zasilka", postman, goods, null, DeliveryStatus.INIT);
        List<Delivery> deliveries = deliveryDao.getAllDeliveries();
    }

    @Test
    public void testCreateDeliveryWithBadStatus() {
        service.createDelivery("Obchodni zasilka", postman, goods, null, DeliveryStatus.DELIVERED);
        List<Delivery> deliveries = deliveryDao.getAllDeliveries();
    }

    @Test
    public void testDeleteDelivery() {
        deliveryDao.addDelivery(deliveryOne);
        List<Delivery> deliveries = deliveryDao.getAllDeliveries();
        assertEquals(deliveries.size(), 1, "Database should contains one delivery.");
        service.deleteDelivery(deliveryOne);
        deliveries = deliveryDao.getAllDeliveries();
        assertEquals(deliveries.size(), 0, "Database should be empty.");
    }

    @Test
    public void testDeleteNullDelivery() {
        exception.expect(NullPointerException.class);
        service.deleteDelivery(null);
    }

    @Test
    public void testDeleteNonExistDelivery() {
        exception.expect(DataAccessException.class);
        service.deleteDelivery(deliveryOne);
    }

    @Test
    public void testUpdateDeliveryNullDelivery() {
        exception.expect(NullPointerException.class);
        service.updateDelivery(null);
    }

    @Test
    public void testUpdateDeliveryName() {
        deliveryDao.addDelivery(deliveryOne);
        List<Delivery> deliveries = deliveryDao.getAllDeliveries();
        assertEquals(deliveries.size(), 1, "Database should contains one delivery.");
        deliveryOne.setName("UpdatedName");
        service.updateDelivery(deliveryOne);
        deliveries = deliveryDao.getAllDeliveries();
        assertEquals(deliveries.size(), 1, "Database should be empty.");
        assertEquals(deliveries.get(0).getName(), "UpdatedName", "Delivery name should be updated.");
    }

    @Test
    public void testUpdateDeliveryPostman() {
        deliveryDao.addDelivery(deliveryOne);
        List<Delivery> deliveries = deliveryDao.getAllDeliveries();
        assertEquals(deliveries.size(), 1, "Database should contains one delivery.");
        deliveryOne.setPostman(updatedPostman);
        service.updateDelivery(deliveryOne);
        deliveries = deliveryDao.getAllDeliveries();
        assertEquals(deliveries.size(), 1, "Database should be empty.");
        assertEquals(deliveries.get(0).getPostman(), updatedPostman, "Delivery postman should be updated.");
    }

    @Test
    public void testUpdateDeliveryGoods() {
        deliveryDao.addDelivery(deliveryOne);
        List<Delivery> deliveries = deliveryDao.getAllDeliveries();
        assertEquals(deliveries.size(), 1, "Database should contains one delivery.");
        deliveryOne.setGoods(updatedGoods);
        service.updateDelivery(deliveryOne);
        deliveries = deliveryDao.getAllDeliveries();
        assertEquals(deliveries.size(), 1, "Database should be empty.");
        assertEquals(deliveries.get(0).getGoods(), updatedGoods, "Delivery goods should be updated.");
    }

    @Test
    public void testUpdateDeliveryCustomer() {
        deliveryDao.addDelivery(deliveryOne);
        List<Delivery> deliveries = deliveryDao.getAllDeliveries();
        assertEquals(deliveries.size(), 1, "Database should contains one delivery.");
        deliveryOne.setCustomer(updatedCustomer);
        service.updateDelivery(deliveryOne);
        deliveries = deliveryDao.getAllDeliveries();
        assertEquals(deliveries.size(), 1, "Database should be empty.");
        assertEquals(deliveries.get(0).getCustomer(), updatedCustomer, "Delivery customer should be updated.");
    }

    @Test
    public void testUpdateDeliveryStatus() {
        deliveryDao.addDelivery(deliveryOne);
        List<Delivery> deliveries = deliveryDao.getAllDeliveries();
        assertEquals(deliveries.size(), 1, "Database should contains one delivery.");
        deliveryOne.setStatus(DeliveryStatus.SENT);
        service.updateDelivery(deliveryOne);
        deliveries = deliveryDao.getAllDeliveries();
        assertEquals(deliveries.size(), 1, "Database should be empty.");
        assertEquals(deliveries.get(0).getStatus(), DeliveryStatus.SENT, "Delivery customer should be updated.");
    }

    @Test
    public void testGetAllDeliveries() {
        deliveryDao.addDelivery(deliveryOne);
        deliveryDao.addDelivery(deliveryTwo);
        List<Delivery> deliveries = service.getAllDeliveries();
        assertEquals(deliveries.size(), 1);
        assertEquals(deliveries.get(0), deliveryOne);
    }

    @Test
    public void testGetAllDeliveriesFromEmptyDatabase() {
        List<Delivery> deliveries = service.getAllDeliveries();
        assertTrue(deliveries.isEmpty());

    }

    @Test
    public void testFindDelivery() {
        deliveryDao.addDelivery(deliveryOne);
        deliveryDao.addDelivery(deliveryTwo);

        Delivery deliveryDb = service.findDelivery(deliveryTwo.getId());
        assertEquals(deliveryDb.getName(), "Zasilka pro Prahu");
        assertEquals(deliveryDb.getStatus(), DeliveryStatus.SENT);
    }

    @Test
    public void testFindDeliveryWithNegativeId() {
        exception.expect(IllegalArgumentException.class);
        service.findDelivery(-8L);
    }

    @Test
    public void testFindDeliveryWithNonExistElement() {
        deliveryDao.addDelivery(deliveryOne);
        deliveryDao.addDelivery(deliveryTwo);
        exception.expect(DataAccessException.class);
        Delivery deliveryDb = service.findDelivery(3L);
    }

}
