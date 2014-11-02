/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.servicelayer.serviceImplementation;

import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import pa165.deliveryservice.daoImplementation.GoodsDaoImpl;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.Goods;
import pa165.servicelayer.dto.GoodsDto;
import pa165.servicelayer.serviceInterface.GoodsService;

/**
 *
 * @author Drimal
 */
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDaoImpl goodsDao;

    @Autowired
    private Mapper mapper;

    @Override
    public Goods createGoods(long price, String seller, Delivery delivery) {
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
        goods.setDelivery(delivery);
        try {
            goodsDao.addGoods(goods);
        } catch (Exception ex) {
            throw new DataAccessException("Error in persistance layer.", ex) {
            };
        }
        return goods;
    }

    @Override
    public boolean deleteGoods(Goods goods) {
        boolean result = false;
        if (goods == null) {
            throw new NullPointerException("Goods can't be null.");
        }
        try {
            goodsDao.deleteGoods(goods);
            return true;
        } catch (Exception ex) {
            throw new DataAccessException("Error in persistance layer.", ex) {
            };
        }
    }

    @Override
    public void updateGoods(Goods goods) {
        if (goods == null) {
            throw new NullPointerException("Goods can't be null.");
        }
        try {
            goodsDao.updateGoods(goods);
        } catch (Exception ex) {
            throw new DataAccessException("Error in persistance layer.", ex) {
            };
        }
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
        try {
            goods = goodsDao.getGoods(id);
        } catch (Exception ex) {
            throw new DataAccessException("Error in persistance layer.", ex) {
            };
        }
        return mapper.map(goods, GoodsDto.class);
    }

}
