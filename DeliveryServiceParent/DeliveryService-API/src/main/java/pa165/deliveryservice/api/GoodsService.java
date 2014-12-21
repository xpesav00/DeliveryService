package pa165.deliveryservice.api;

import java.util.List;
import pa165.deliveryservice.api.dto.DeliveryDto;
import pa165.deliveryservice.api.dto.GoodsDto;

/**
 * Service layer Goods interface.
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
    
    /**
     * Deletes goods
     * @param goods
     * @return 
     */
    boolean deleteGoods(GoodsDto goods);
    
    /**
     * Updates goods
     * @param goods 
     */
    void updateGoods(GoodsDto goods);
    
    /**
     * Return list of all goods in db
     * @return 
     */
    List<GoodsDto> getAllGoods();
    
    /**
     * Find goods by given id
     * @param id id of goods
     * @return GoodsDto with given id or null if there doesn't exist
     */
    GoodsDto findGood(long id);
}
