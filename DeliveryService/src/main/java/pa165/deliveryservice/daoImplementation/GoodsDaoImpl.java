package pa165.deliveryservice.daoImplementation;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import pa165.deliveryservice.daoInterface.GoodsDao;
import pa165.deliveryservice.entity.Goods;

/**
 * Implementation of necessary operations with goods entities
 * 
 * @author Martin Drimal
 */
public class GoodsDaoImpl implements GoodsDao {
    private static final Logger log = LoggerFactory.getLogger(GoodsDaoImpl.class);
    private EntityManagerFactory emf;

    public GoodsDaoImpl() {
        log.info("Create database connection.");
        emf = Persistence.createEntityManagerFactory("myUnit");
    }
    
    public GoodsDaoImpl(EntityManagerFactory emf){
        if(emf == null){
            throw new NullPointerException();
        }
        this.emf = emf;
    }

    @Override
    public List<Goods> getAllGoods() {
        log.info("Retrieve all goods from database.");
        EntityManager em = emf.createEntityManager();
        List<Goods > goods = em.createQuery("SELECT g FROM Goods g", Goods.class).getResultList(); 
        em.close();
        return goods;
    }

    @Override
    public void updateGoods(Goods goods) throws IllegalArgumentException, NullPointerException{
        log.info("Update goods "+goods+" to database.");
        if(goods == null){
            throw new NullPointerException("Goods is null.");
        }
        EntityManager em = emf.createEntityManager(); 
       em.getTransaction().begin();
        em.merge(goods);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteGoods(Goods goods) throws IllegalArgumentException{
        log.info("Delete goods "+goods+" from database.");
        if(goods == null){
            throw new NullPointerException("Goods is null.");
        }        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Goods goodsDb = em.find(Goods.class, goods.getId());
        em.remove(goodsDb);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void addGoods(Goods goods) {
        log.info("Add goods "+goods+" to database.");
        if (goods == null) {
            throw new NullPointerException("Goods is null.");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(goods);
        em.getTransaction().commit();
        em.close();
    }
    
    @Override
    public Goods getGoods(long id){
        log.info("Get specific goods from database.");
        if (id <= 0){
            throw new IllegalArgumentException("Id is zero or negative.");
        }
        EntityManager em = emf.createEntityManager();
        Goods goodsDb = null;
        try{
            goodsDb = em.find(Goods.class, id);
        }catch(NullPointerException ex){
            throw new IllegalArgumentException("Unknow delivery object.");
        }
        em.close();
        
        return goodsDb;
    }
    
    @Override
    public void closeConnection(){
        if(emf != null){
            emf.close();
        }
    }
    
    private EntityManager createEntityManager(){
        emf = Persistence.createEntityManagerFactory("myUnit");
        return emf.createEntityManager();
    }
}
