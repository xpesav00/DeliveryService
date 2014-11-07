package pa165.deliveryservice.daoInterface;


import java.util.List;
import pa165.deliveryservice.entity.Goods;

/**
 * Performs necessary operations with goods entities
 * 
 * @author Martin Drimal
 */
public interface GoodsDao {
    /**
     * Retrieve all goods from Delivery Service database
     * @return list of goods
     */
    List<Goods> getAllGoods();

    /**
     * Update goods in Delivery Service database
     * @param goods goods to update in database
     */
    void updateGoods(Goods goods);

    /**
     * Delete goods from Delivery Service database
     * @param goods goods to delete from database
     */
    void deleteGoods(Goods goods);

    /**
     * Add goods to Delivery Service database
     * @param goods goods to add into database
     */
    void addGoods(Goods goods);
    
    /**
     * Get specific goods to Delivery Service database
     * @param goods goods to add into database
     */
    Goods getGoods(long id);

    /**
     * Close opened connection
     */
    void closeConnection();
}