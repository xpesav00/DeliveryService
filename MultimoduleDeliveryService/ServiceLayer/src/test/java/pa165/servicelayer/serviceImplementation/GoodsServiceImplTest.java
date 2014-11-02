/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.servicelayer.serviceImplementation;

import java.util.List;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import pa165.deliveryservice.daoImplementation.GoodsDaoImpl;
import pa165.deliveryservice.daoInterface.GoodsDao;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.DeliveryStatus;
import pa165.deliveryservice.entity.Goods;
import pa165.servicelayer.serviceInterface.GoodsService;
import static org.testng.Assert.*;

/**
 *
 * @author Jan Stastny
 */
@RunWith(MockitoJUnitRunner.class)
public class GoodsServiceImplTest {
    
    @Mock
    private Delivery delivery;
    private GoodsService service;
    private GoodsDao dao;
    private Goods testingGoods;
    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Before
    public void setUp() {
        delivery = new Delivery();
        delivery.setName("Zasilka100000");
        delivery.setCustomer(null);
        delivery.setPostman(null);
        delivery.setGoods(null);
        delivery.setStatus(DeliveryStatus.INIT);
        
        testingGoods = new Goods();
        testingGoods.setPrice(1000);
        testingGoods.setSeller("Tescoma");
        testingGoods.setDelivery(delivery);
        
        service = new GoodsServiceImpl();
        dao = new GoodsDaoImpl();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testCreateGoods() {
        long price = 2500;
        String seller = "Tescoma";
        
        service.createGoods(price, seller, delivery);
        List<Goods> allGoods = dao.getAllGoods();
        boolean assertB = allGoods.size()==1;
        assertTrue(assertB,"In DB should be one delivery.");
        Goods g = allGoods.get(0);
        assertEquals(g.getPrice(), price);
        assertEquals(g.getSeller(), seller);
        assertEquals(g.getDelivery(), delivery);
    }
    
    public void testCreateGoodsWithWrongPrice() {
        long price = -1;
        String seller = "Tescoma";
        service.createGoods(price, seller, delivery);
        List<Goods> allGoods = dao.getAllGoods();
        assertTrue(allGoods.isEmpty(),"Price can not be negative value.");
    }
    
    public void testCreateGoodsWithToLongSeller() {
        long price = 1000;
        String seller = "SomethingWithMoreThanTwentyLetters";
        service.createGoods(price, seller, delivery);
        List<Goods> allGoods = dao.getAllGoods();
        assertTrue(allGoods.isEmpty(),"Wrong length of seller.");
    }
    
    public void testCreateGoodsWithEmptySeller() {
        long price = 1000;
        String seller = "";
        service.createGoods(price, seller, delivery);
        List<Goods> allGoods = dao.getAllGoods();
        assertTrue(allGoods.isEmpty(),"Wrong length of seller.");
    }
    
    public void testCreateGoodsWithNullDelivery() {
        long price = 1000;
        String seller = "Tescoma";
        service.createGoods(price, seller, null);
        List<Goods> allGoods = dao.getAllGoods();
        assertTrue(allGoods.isEmpty(),"Delivery can not be null.");
    }
    
    public void testDeleteGoods() {
        dao.addGoods(testingGoods);
        List<Goods> allGoods = dao.getAllGoods();
        assertEquals(allGoods.size(), 1, "Database should contains one goods.");
        service.deleteGoods(testingGoods);
        allGoods = dao.getAllGoods();
        assertTrue(allGoods.isEmpty(),"DB should be empty");        
    }
    
    public void testDeleteNull() {
        exception.expect(NullPointerException.class);
        service.deleteGoods(null);
    }
    
    public void deleteNonExistentGoods() {
        exception.expect(DataAccessException.class);
        service.deleteGoods(testingGoods);
    }
    
    public void testUpdateNull() {
        exception.expect(NullPointerException.class);
        service.updateGoods(null);
    }
    
    public void testUpdateSeller() {
        dao.addGoods(testingGoods);
        List<Goods> allGoods = dao.getAllGoods();
        assertEquals(allGoods.size(), 1, "Database should contains one goods.");
        testingGoods.setSeller("McDonald");
        service.updateGoods(testingGoods);
        allGoods = service.getAllGoods();
        assertEquals(allGoods.get(0).getSeller(), "McDonald", "Goods new Seller should be McDonald");
    }
    
    public void testGetAllGoods() {
        dao.addGoods(testingGoods);
        List<Goods> allGoods = service.getAllGoods();
        assertEquals(allGoods.size(), 1);
        assertEquals(allGoods.get(0), testingGoods);
    }
    
    public void testFind() {
        dao.addGoods(testingGoods);
        Goods findedGoods = service.findGood(testingGoods.getId());
        assertEquals(testingGoods, findedGoods);
    }
    
    public void testFindWithWrongId() {
        dao.addGoods(testingGoods);
        exception.expect(IllegalArgumentException.class);
        service.findGood(-1);
    }
    
}
