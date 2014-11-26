package pa165.deliveryservice.daoImplementation;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pa165.deliveryservice.daoInterface.GoodsDao;
import pa165.deliveryservice.entity.Goods;

/**
 * Implementation of necessary operations with goods entities
 *
 * @author Martin Drimal
 */
@Repository
@Transactional
public class GoodsDaoImpl implements GoodsDao {

    private static final Logger log = LoggerFactory.getLogger(GoodsDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    public GoodsDaoImpl() {
        log.info("Create database connection.");
    }

    public GoodsDaoImpl(EntityManager em) {
        log.info("Create database connection.");
        if (em == null) {
            throw new NullPointerException();
        }
        this.em = em;
    }

    @Override
    public List<Goods> getAllGoods() {
        log.info("Retrieve all goods from database.");
        List<Goods> goods = em.createQuery("SELECT g FROM Goods g", Goods.class).getResultList();
        return goods;
    }

    @Override
    public void updateGoods(Goods goods) throws IllegalArgumentException, NullPointerException {
        log.info("Update goods " + goods + " to database.");
        if (goods == null) {
            throw new NullPointerException("Goods is null.");
        }
        em.merge(goods);
    }

    @Override
    public void deleteGoods(Goods goods) throws IllegalArgumentException {
        log.info("Delete goods " + goods + " from database.");
        if (goods == null) {
            throw new NullPointerException("Goods is null.");
        }
        Goods goodsDb = null;
        try {
            goodsDb = em.find(Goods.class, goods.getId());
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("Unknown object to delete.");
        }
        em.remove(goodsDb);
    }

    @Override
    public void addGoods(Goods goods) {
        log.info("Add goods " + goods + " to database.");
        if (goods == null) {
            throw new NullPointerException("Goods is null.");
        }
        em.persist(goods);
    }

    @Override
    public Goods getGoods(long id) {
        log.info("Get specific goods from database.");
        if (id <= 0) {
            throw new IllegalArgumentException("Id is zero or negative.");
        }
        Goods goodsDb = null;
        try {
            goodsDb = em.find(Goods.class, id);
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("Unknow goods object.");
        }
        return goodsDb;
    }
    
    @Override
    public void closeConnection(){
        if(em != null){
            em.close();
        }
    }
}