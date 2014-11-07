/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.servicelayer.serviceImplementation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import pa165.deliveryservice.daoImplementation.GoodsDaoImpl;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.Goods;
import pa165.servicelayer.dto.DeliveryDto;

/**
 *
 * @author Jan Stastny
 */
@RunWith(MockitoJUnitRunner.class)
public class GoodsServiceImplTest {
    
    @Mock
    private GoodsDaoImpl goodsDao;
    @InjectMocks
    GoodsServiceImpl service;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testCreate() {
        //TO DO log.info("testing create.");
        DeliveryDto deliveryDTO = new DeliveryDto();
        Goods goods = new Goods();
        long price = 1000;
        String seller = "Tescoma";
        service.createGoods(price, seller, deliveryDTO);
        verify(goodsDao, Mockito.times(1)).addGoods(goods);
    }
/*****OLD VERSION*****************/    
//    @Mock
//    private Delivery delivery;
//    private GoodsService service;
//    private GoodsDao dao;
//    private Goods testingGoods;
//    private Mapper mapper;
//    
//    @Rule
//    public ExpectedException exception = ExpectedException.none();
//    @Before
//    public void setUp() {
//        delivery = new Delivery();
//        delivery.setName("Zasilka100000");
//        delivery.setCustomer(null);
//        delivery.setPostman(null);
//        delivery.setGoods(null);
//        delivery.setStatus(DeliveryStatus.INIT);
//        
//        testingGoods = new Goods();
//        testingGoods.setPrice(1000);
//        testingGoods.setSeller("Tescoma");
//        testingGoods.setDelivery(delivery);
//        
//        service = new GoodsServiceImpl();
//        dao = new GoodsDaoImpl();
//    }
//    
//    @After
//    public void tearDown() {
//    }
//    
//    @Test
//    public void testCreateGoods() {
//        long price = 2500;
//        String seller = "Tescoma";
//        
//        service.createGoods(price, seller, mapper.map(delivery,DeliveryDto.class));
//        List<Goods> allGoods = dao.getAllGoods();
//        boolean assertB = allGoods.size()==1;
//        assertTrue(assertB,"In DB should be one delivery.");
//        Goods g = allGoods.get(0);
//        assertEquals(g.getPrice(), price);
//        assertEquals(g.getSeller(), seller);
//        assertEquals(g.getDelivery(), delivery);
//    }
//    
//    public void testCreateGoodsWithWrongPrice() {
//        long price = -1;
//        String seller = "Tescoma";
//        service.createGoods(price, seller, mapper.map(delivery,DeliveryDto.class));
//        List<Goods> allGoods = dao.getAllGoods();
//        assertTrue(allGoods.isEmpty(),"Price can not be negative value.");
//    }
//    
//    public void testCreateGoodsWithToLongSeller() {
//        long price = 1000;
//        String seller = "SomethingWithMoreThanTwentyLetters";
//        service.createGoods(price, seller, mapper.map(delivery,DeliveryDto.class));
//        List<Goods> allGoods = dao.getAllGoods();
//        assertTrue(allGoods.isEmpty(),"Wrong length of seller.");
//    }
//    
//    public void testCreateGoodsWithEmptySeller() {
//        long price = 1000;
//        String seller = "";
//        service.createGoods(price, seller, mapper.map(delivery,DeliveryDto.class));
//        List<Goods> allGoods = dao.getAllGoods();
//        assertTrue(allGoods.isEmpty(),"Wrong length of seller.");
//    }
//    
//    public void testCreateGoodsWithNullDelivery() {
//        long price = 1000;
//        String seller = "Tescoma";
//        service.createGoods(price, seller, null);
//        List<Goods> allGoods = dao.getAllGoods();
//        assertTrue(allGoods.isEmpty(),"Delivery can not be null.");
//    }
//    
//    public void testDeleteGoods() {
//        dao.addGoods(testingGoods);
//        List<Goods> allGoods = dao.getAllGoods();
//        assertEquals(allGoods.size(), 1, "Database should contains one goods.");
//        service.deleteGoods(mapper.map(testingGoods,GoodsDto.class));
//        allGoods = dao.getAllGoods();
//        assertTrue(allGoods.isEmpty(),"DB should be empty");        
//    }
//    
//    public void testDeleteNull() {
//        exception.expect(NullPointerException.class);
//        service.deleteGoods(null);
//    }
//    
//    public void deleteNonExistentGoods() {
//        exception.expect(DataAccessException.class);
//        service.deleteGoods(mapper.map(testingGoods,GoodsDto.class));
//    }
//    
//    public void testUpdateNull() {
//        exception.expect(NullPointerException.class);
//        service.updateGoods(null);
//    }
//    
//    public void testUpdateSeller() {
//        dao.addGoods(testingGoods);
//        List<Goods> allGoods = dao.getAllGoods();
//        assertEquals(allGoods.size(), 1, "Database should contains one goods.");
//        testingGoods.setSeller("McDonald");
//        service.updateGoods(mapper.map(testingGoods,GoodsDto.class));
//        List<GoodsDto> allGoodsDto = service.getAllGoods();
//        assertEquals(allGoodsDto.get(0).getSeller(), "McDonald", "Goods new Seller should be McDonald");
//    }
//    
//    public void testGetAllGoods() {
//        dao.addGoods(testingGoods);
//        List<GoodsDto> allGoods = service.getAllGoods();
//        assertEquals(allGoods.size(), 1);
//        assertEquals(allGoods.get(0), testingGoods);
//    }
//    
//    public void testFind() {
//        dao.addGoods(testingGoods);
//        GoodsDto findedGoods = service.findGood(testingGoods.getId());
//        assertEquals(testingGoods, findedGoods);
//    }
//    
//    public void testFindWithWrongId() {
//        dao.addGoods(testingGoods);
//        exception.expect(IllegalArgumentException.class);
//        service.findGood(-1);
//    }
    
}
