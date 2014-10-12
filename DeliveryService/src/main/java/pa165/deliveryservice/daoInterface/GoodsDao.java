package pa165.deliveryservice.daoInterface;

import pa165.deliveryservice.entity.Goods;
import java.util.List;

public interface GoodsDao {

    List<Goods> getAllGoods();

    void updateGoods();

    void deleteGoods();

    void addGoods();

}
