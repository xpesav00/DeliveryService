package daoImplementation;

import daoInterface.GoodsDao;
import entity.Goods;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GoodsDaoImpl implements GoodsDao {

    private EntityManagerFactory emf;

    public List<Goods> getAllGoods() {
        EntityManager em = createEntityManager();
        List<Goods > goods = em.createQuery("SELECT g FROM Goods g", Goods.class).getResultList(); 
        em.close();
        emf.close();
        return goods;
    }

    public void updateGoods(Goods goods) {
        EntityManager em = createEntityManager();
        em.getTransaction().begin();
        em.merge(goods);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public void deleteGoods(Goods goods) {
        EntityManager em = createEntityManager();
        em.getTransaction().begin();
        em.remove(goods);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public void addGoods(Goods goods) {
        EntityManager em = createEntityManager();
        em.getTransaction().begin();
        em.persist(goods);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
    
    private EntityManager createEntityManager(){
        emf = Persistence.createEntityManagerFactory("myUnit");
        return emf.createEntityManager();
    }
}
