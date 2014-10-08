package daoInterface;

import entity.Goods;
import java.util.List;

public interface GoodsDao {

    List<Goods> getAllGoods();

    void updateGoods(Goods goods);

    void deleteGoods(Goods goods);

    void addGoods(Goods goods);

}
