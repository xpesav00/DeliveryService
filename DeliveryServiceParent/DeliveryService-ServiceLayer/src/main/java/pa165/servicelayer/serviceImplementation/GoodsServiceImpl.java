package pa165.servicelayer.serviceImplementation;

import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pa165.deliveryservice.daoInterface.GoodsDao;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.Goods;
import pa165.deliveryservice.api.dto.DeliveryDto;
import pa165.deliveryservice.api.dto.GoodsDto;
import pa165.deliveryservice.api.GoodsService;

/**
 *
 * @author Drimal
 */
@Service("goodsService")
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private Mapper mapper;

    @Override
    @Secured("ROLE_ADMIN")
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
    @Secured("ROLE_ADMIN")
    public boolean deleteGoods(GoodsDto goodsDto) {
        if (goodsDto == null) {
            throw new NullPointerException("Goods can't be null.");
        }

        Goods goods = convertGoodsDtoToGoods(goodsDto);
        goodsDao.deleteGoods(goods);
        return true;
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void updateGoods(GoodsDto goodsDto) {
        if (goodsDto == null) {
            throw new NullPointerException("Goods can't be null.");
        }
        Goods goods = convertGoodsDtoToGoods(goodsDto);

        goodsDao.updateGoods(goods);
    }

    @Override
    @Secured({"ROLE_USER", "ROLE_ADMIN","ROLE_POSTMAN"})
    public List<GoodsDto> getAllGoods() {
        List<GoodsDto> resultList = new ArrayList<>();
        for (Goods goods : goodsDao.getAllGoods()) {
            resultList.add(mapper.map(goods, GoodsDto.class));
        }
        return resultList;
    }

    @Override
    @Secured({"ROLE_USER", "ROLE_ADMIN","ROLE_POSTMAN"})
    public GoodsDto findGood(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id can't be negative.");
        }
        Goods goods = goodsDao.getGoods(id);

        return mapper.map(goods, GoodsDto.class);
    }

    private Goods convertGoodsDtoToGoods(GoodsDto goodsDto) throws MappingException {
        Goods goods = new Goods();
        goods.setId(goodsDto.getId());
        goods.setPrice(goodsDto.getPrice());
        goods.setSeller(goodsDto.getSeller());
        goods.setDelivery(mapper.map(goodsDto.getDelivery(), Delivery.class));
        return goods;
    }
}
