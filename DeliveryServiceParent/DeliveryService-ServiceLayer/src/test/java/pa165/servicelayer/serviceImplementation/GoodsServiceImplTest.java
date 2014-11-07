package pa165.servicelayer.serviceImplementation;
 
import static junit.framework.Assert.assertEquals;
import org.dozer.DozerBeanMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import pa165.deliveryservice.daoInterface.GoodsDao;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.Goods;
import pa165.servicelayer.dto.GoodsDto;
import pa165.servicelayer.serviceInterface.GoodsService;
 
 
/**
*
* @author Jan Stastny
*/
@RunWith(MockitoJUnitRunner.class)
public class GoodsServiceImplTest extends AbstractIntegrationTest {
    
    private GoodsService goodsService;
    
    private DozerBeanMapper mapper;
    private Goods goods;
    private GoodsDto goodsDTO;
   
    @Mock
    GoodsDao goodsDAO;
    
    @Mock
    private Delivery delivery;
   
    @Before
    public void setUp() throws Exception {
        goodsService = new GoodsServiceImpl();
        mapper = new DozerBeanMapper();
        ReflectionTestUtils.setField(goodsService, "goodsDao", goodsDAO);
        ReflectionTestUtils.setField(goodsService, "mapper", mapper);
       
        goods = new Goods();
        goods.setPrice(1000);
        goods.setSeller("Tescoma");
        goods.setDelivery(delivery);
       
        goodsDTO = mapper.map(goods, GoodsDto.class);
        when(goodsDAO.getGoods(goods.getId())).thenReturn(goods);
    }
   
    @After
    public void tearDown() throws Exception {
        goodsService = null;
        mapper = null;
        goods = null;
        goodsDTO = null;
    }
  
    @Test
    public void testAddGoods() {
        goodsService.createGoods(goodsDTO.getPrice(), goodsDTO.getSeller(), goodsDTO.getDelivery());
        verify(goodsDAO).addGoods(goods);
    }
   
    @Test
    public void testDeleteGoods() {
        goodsService.deleteGoods(goodsDTO);
        verify(goodsDAO).deleteGoods(goods);
    }
   
    @Test
    public void testFindGoods() {
        when(goodsDAO.getGoods(goodsDTO.getId())).thenReturn(goods);
        GoodsDto gDTO = goodsService.findGood(goodsDTO.getId());
        assertEquals(gDTO.getSeller(), goodsDTO.getSeller());
    }
   
    @Test
    public void testEditGoods() {
        goodsService.updateGoods(goodsDTO);
        verify(goodsDAO).updateGoods(goods);
    }
} 