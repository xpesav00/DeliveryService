package daoInterface;

import entity.Goods;
import java.util.List;

public interface GoodsDao {

    List<Goods> getAllGoods();

    void updateGoods();

    void deleteGoods();

    void addGoods();

}
