package pa165.servicelayer.serviceInterface;

import java.util.List;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.Goods;
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
    Goods createGoods(long price, String seller, Delivery delivery);
    
    boolean deleteGoods(Goods goods);
    
    void updateGoods(Goods goods);
    
    List<GoodsDto> getAllGoods();
    
    GoodsDto findGood(long id);
}
