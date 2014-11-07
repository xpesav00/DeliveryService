package pa165.servicelayer.serviceInterface;

import java.util.List;
import pa165.servicelayer.dto.DeliveryDto;
import pa165.servicelayer.dto.GoodsDto;

/**
 *
 * @author Drimal
 */
public interface GoodsService {
    /**
     * Create goods and put into database
     * @param price goods price
     * @param seller goods seller
     * @param delivery 
     * @return created goods
     */
    GoodsDto createGoods(long price, String seller, DeliveryDto delivery);
    
    boolean deleteGoods(GoodsDto goods);
    
    void updateGoods(GoodsDto goods);
    
    List<GoodsDto> getAllGoods();
    
    GoodsDto findGood(long id);
}
