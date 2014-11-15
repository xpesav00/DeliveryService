package pa165.deliveryservice.daoImplementation;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pa165.deliveryservice.DaoContext;
import pa165.deliveryservice.daoInterface.GoodsDao;
import pa165.deliveryservice.entity.*;

/**
 * Test of GoodsDaoImpl
 *
 * @author Jan Šťastný
 */
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GoodsDaoImplNGTest extends AbstractTestNGSpringContextTests {

    private EntityManagerFactory emf;
    private EntityManager em;
    private GoodsDao daoInstance;

    @BeforeMethod
    public void setUpMethod() throws Exception {
        emf = Persistence.createEntityManagerFactory("myUnit");
        em = emf.createEntityManager();
        daoInstance = new GoodsDaoImpl(em);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        emf.close();
    }

    /**
     * Test of getAllGoods method, of class GoodsDaoImpl.
     */
    @Test
    public void testGetAllGoods() {
        prepareDatabase();

        List expResult = retrieveDatabase();
        List result = daoInstance.getAllGoods();
        assertEquals(result.size(), expResult.size(), "Number of row should be same.");
        assertEquals(result, expResult, "Retrieved databases should be same.");
    }

    /**
     * Test of updateGoods method, of class GoodsDaoImpl.
     */
    @Test
    public void testUpdateGoods() {
        //prepare database for testing
        em.getTransaction().begin();
        Goods goods = new Goods();
        goods.setPrice(100L);
        goods.setSeller("CZC.cz");
        em.persist(goods);
        em.getTransaction().commit();

        //retrieve objet from database
        em.getTransaction().begin();
        Goods updatedGoods = em.find(Goods.class, goods.getId());
        em.detach(updatedGoods);
        em.getTransaction().commit();

        //update object
        updatedGoods.setPrice(50L);
        updatedGoods.setSeller("Alza.cz");
        em.getTransaction().begin();
        daoInstance.updateGoods(goods);
        em.getTransaction().commit();

        Goods goodsDb = em.find(Goods.class, updatedGoods.getId());
        assertEquals(goodsDb.getPrice(), 50, "Prises should be equal.");
        assertEquals(goodsDb.getSeller(), "Alza.cz", "Sellers should be equal.");
    }

    /**
     * Test of deleteGoods method, of class GoodsDaoImpl.
     */
    @Test
    public void testDeleteGoods() {
        //prepare database for testing
        em.getTransaction().begin();
        Goods goods = new Goods();
        goods.setPrice(100L);
        goods.setSeller("CZC.cz");
        em.persist(goods);
        em.getTransaction().commit();

        em.getTransaction().begin();
        daoInstance.deleteGoods(goods);
        em.getTransaction().commit();

        int currentSize = getNumberOfGoodsInDb();
        assertEquals(currentSize, 0, "Database should contain same number of records.");
    }

    /**
     * Test of addGoods method, of class GoodsDaoImpl.
     */
    @Test
    public void testAddGoods() {
        Goods goods = new Goods();
        goods.setPrice(1000L);
        goods.setSeller("Mall shop");

        em.getTransaction().begin();
        daoInstance.addGoods(goods);
        em.getTransaction().commit();
        
        int currentSize = getNumberOfGoodsInDb();
        assertEquals(currentSize, 1, "Should be the same.");
        
        em.getTransaction().begin();
        Goods goodsDb = em.createQuery("SELECT g FROM Goods g", Goods.class).getResultList().get(0);
        em.getTransaction().commit();
        
        assertEquals(goodsDb.getPrice(), goods.getPrice(), "Goods price is not same.");
        assertEquals(goodsDb.getSeller(), goods.getSeller(), "Goods seller is not same.");
    }

    private void prepareDatabase() {
        EntityManager em = emf.createEntityManager();

        Goods goods1 = new Goods();
        goods1.setPrice(1000);
        goods1.setSeller("AAA");

        Goods goods2 = new Goods();
        goods2.setPrice(5000);
        goods2.setSeller("BB");

        Goods goods3 = new Goods();
        goods3.setPrice(1);
        goods3.setSeller("CCC");

        em.getTransaction().begin();
        em.persist(goods1);
        em.persist(goods2);
        em.persist(goods3);
        em.getTransaction().commit();
        em.close();
    }

    private List<Goods> retrieveDatabase() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("Select g FROM Goods g").getResultList();
    }

    private int getNumberOfGoodsInDb() {
        List<Goods> list = em.createQuery("SELECT g FROM Goods g", Goods.class).getResultList();
        return list.size();
    }
}