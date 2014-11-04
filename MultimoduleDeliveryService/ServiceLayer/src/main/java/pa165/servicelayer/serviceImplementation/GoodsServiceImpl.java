package pa165.servicelayer.serviceImplementation;

import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pa165.deliveryservice.daoImplementation.GoodsDaoImpl;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.Goods;
import pa165.servicelayer.dto.DeliveryDto;
import pa165.servicelayer.dto.GoodsDto;
import pa165.servicelayer.serviceInterface.GoodsService;

/**
 *
 * @author Drimal
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDaoImpl goodsDao;

    @Autowired
    private Mapper mapper;

    @Override
    public GoodsDto createGoods(long price, String seller, DeliveryDto delivery) {
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be negative.");
        }
        if (seller.isEmpty()) {
            throw new IllegalArgumentException("Seller can't be empty.");
        }
        if (delivery == null) {
            throw new NullPointerException("Delivery can't be null.");
        }
        Goods goods = new Goods();
        goods.setPrice(price);
        goods.setSeller(seller);
        goods.setDelivery(mapper.map(delivery, Delivery.class));

        goodsDao.addGoods(goods);

        return mapper.map(goods, GoodsDto.class);
    }

    @Override
    public boolean deleteGoods(GoodsDto goods) {
        boolean result = false;
        if (goods == null) {
            throw new NullPointerException("Goods can't be null.");
        }
        try {
            Goods deletedGoods = mapper.map(goods, Goods.class);
            goodsDao.deleteGoods(deletedGoods);
            return true;
        } catch (Exception ex) {
            throw new DataAccessException("Error in persistance layer.", ex) {
            };
        }
    }

    @Override
    public void updateGoods(GoodsDto goods) {
        if (goods == null) {
            throw new NullPointerException("Goods can't be null.");
        }
        Goods updatedGoods = mapper.map(goods, Goods.class);
        goodsDao.updateGoods(updatedGoods);

    }

    @Override
    public List<GoodsDto> getAllGoods() {
        List<GoodsDto> resultList = new ArrayList<>();
        for (Goods goods : goodsDao.getAllGoods()) {
            resultList.add(mapper.map(goods, GoodsDto.class));
        }
        return resultList;
    }

    @Override
    public GoodsDto findGood(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id can't be negative.");
        }
        Goods goods = null;
        goods = goodsDao.getGoods(id);

        return mapper.map(goods, GoodsDto.class);
    }

}
