package pa165.servicelayer.serviceImplementation;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.Goods;
import pa165.deliveryservice.entity.Postman;

/**
 *
 * @author Drimal
 */
 @RunWith(MockitoJUnitRunner.class)
public class DeliveryServiceImplTest {
    @Mock
    private Postman postman;
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createGoods method, of class GoodsServiceImpl.
     */
    @Test
    public void testCreateGoods() {
        System.out.println("createGoods");
        long price = 0L;
        String seller = "";
        Delivery delivery = null;
        GoodsServiceImpl instance = new GoodsServiceImpl();
        Goods expResult = null;
        Goods result = instance.createGoods(price, seller, delivery);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteGoods method, of class GoodsServiceImpl.
     */
    @Test
    public void testDeleteGoods() {
        System.out.println("deleteGoods");
        Goods goods = null;
        GoodsServiceImpl instance = new GoodsServiceImpl();
        boolean expResult = false;
        boolean result = instance.deleteGoods(goods);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateGoods method, of class GoodsServiceImpl.
     */
    @Test
    public void testUpdateGoods() {
        System.out.println("updateGoods");
        Goods goods = null;
        GoodsServiceImpl instance = new GoodsServiceImpl();
        instance.updateGoods(goods);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllGoods method, of class GoodsServiceImpl.
     */
    @Test
    public void testGetAllGoods() {
        System.out.println("getAllGoods");
        GoodsServiceImpl instance = new GoodsServiceImpl();
        List<Goods> expResult = null;
        List<Goods> result = instance.getAllGoods();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findGood method, of class GoodsServiceImpl.
     */
    @Test
    public void testFindGood() {
        System.out.println("findGood");
        long id = 0L;
        GoodsServiceImpl instance = new GoodsServiceImpl();
        Goods expResult = null;
        Goods result = instance.findGood(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
