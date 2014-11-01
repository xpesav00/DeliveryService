package pa165.servicelayer.serviceImplementation;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
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
 @RunWith(MockitoJUnitRunner.class)
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

    private Delivery delivery;
    
    private DeliveryService service;
    
    @Before
    public void setUp() {
        delivery = new Delivery();
        delivery.setName("Rodinna zasilka");
        delivery.setPostman(postman);
        delivery.setGoods(goods);
        delivery.setCustomer(customer);
        delivery.setStatus(DeliveryStatus.INIT);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCreateDelivery() {
        
    }
    @Test
    public void testDeleteDelivery() {
    }

    @Test
    public void testUpdateDeliveryNullDelivery(){
        
    }
 
    @Test
    public void testUpdateDeliveryName() {
    }

    @Test
    public void testUpdateDeliveryPostman() {
    }

    @Test 
    public void testUpdateDeliveryGoods(){
        
    }

    @Test
    public void testUpdateDeliveryCustomer(){
        
    }

    @Test
    public void testUpdateDeliveryStatus(){
        
    }

    @Test
    public void testGetAllDeliveries() {
    }

    @Test
    public void testFindDelivery() {
        
    }
    
}
