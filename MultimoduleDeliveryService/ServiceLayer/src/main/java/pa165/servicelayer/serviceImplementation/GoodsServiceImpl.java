/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.servicelayer.serviceImplementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import pa165.deliveryservice.daoImplementation.GoodsDaoImpl;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.Goods;
import pa165.servicelayer.serviceInterface.GoodsService;

/**
 *
 * @author Drimal
 */
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    private GoodsDaoImpl goodsDao;

    @Override
    public Goods createGoods(long price, String seller, Delivery delivery) {
        if(price < 0){
            throw new IllegalArgumentException("Price can't be negative.");
        }
        if(seller.isEmpty()){
            throw new IllegalArgumentException("Seller can't be empty.");
        }
        if(delivery == null){
            throw new NullPointerException("Delivery can't be null.");
        }
        Goods goods = new Goods();
        goods.setPrice(price);
        goods.setSeller(seller);
        goods.setDelivery(delivery);
        try{
            goodsDao.addGoods(goods);
        }catch(NullPointerException ex){
            throw new DataAccessException("Error in persistance layer."){};
        }
        return goods;
    }

    @Override
    public boolean deleteGoods(Goods goods) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateGoods(Goods goods) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Goods> getAllGoods() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Goods findGood(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
