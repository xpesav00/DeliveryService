/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.deliveryservice.daoImplementation;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.commons.lang3.Validate;
import org.junit.*;
import static org.junit.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pa165.deliveryservice.entity.Goods;

/**
 *
 * @author Jan Šťastný
 */
public class GoodsDaoImplTest {
    EntityManagerFactory emf;
    
    public GoodsDaoImplTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        emf = Persistence.createEntityManagerFactory("myUnit");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        if(emf != null) emf.close();
    }

    /**
     * Test of getAllGoods method, of class GoodsDaoImpl.
     */
    @Test
    public void testGetAllGoods() {
        System.out.println("getAllGoods");
        prepareDTB();
        
        GoodsDaoImpl instance = new GoodsDaoImpl();
        List expResult = getAllGoodsTest();
        List result = instance.getAllGoods();
        assertEquals("Result lists should be the same.",expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateGoods method, of class GoodsDaoImpl.
     */
    @Test
    public void testUpdateGoods() {
        System.out.println("updateGoods");
        GoodsDaoImpl instance = new GoodsDaoImpl();
        Goods goods = new Goods();
        goods.setPrice(100);
        Goods goodsFromDB = getGoodsFromDB(goods);
        goodsFromDB.setPrice(2000);
        goodsFromDB.setSeller("ABC");
        instance.updateGoods(goodsFromDB);
        
        goods = getGoodsFromDB(goodsFromDB);
        
        assertEquals("Price should equals.",goods.getPrice(), 2000);
        assertEquals("Seller should equals.",goods.getSeller(), "ABC");
    }

    /**
     * Test of deleteGoods method, of class GoodsDaoImpl.
     */
    @Test
    public void testDeleteGoods() {
        System.out.println("deleteGoods");
//        
//        
//        GoodsDaoImpl instance = new GoodsDaoImpl();
//        instance.deleteGoods(deletedGoods);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addGoods method, of class GoodsDaoImpl.
     */
    @Test
    public void testAddGoods() {
        System.out.println("addGoods");
        Goods goods = null;
        GoodsDaoImpl instance = new GoodsDaoImpl();
        instance.addGoods(goods);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeConnection method, of class GoodsDaoImpl.
     */
    @Test
    public void testCloseConnection() {
        System.out.println("closeConnection");
        GoodsDaoImpl instance = new GoodsDaoImpl();
        instance.closeConnection();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    private void prepareDTB() {
        EntityManager em = emf.createEntityManager();
        
        Goods goods1 = new Goods();
        goods1.setPrice(1500);
        goods1.setSeller("AAA");
        
        Goods goods2 = new Goods();
        goods2.setPrice(0);
        goods2.setSeller("BBB");
        
        Goods goods3 = new Goods();
        goods3.setPrice(250);
        goods3.setSeller("CCC");
        
        em.getTransaction().begin();
        em.persist(goods1);
        em.persist(goods2);
        em.persist(goods3);
        em.getTransaction().commit();
        em.close();
    }
    
    private List<Goods> getAllGoodsTest() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("Select g FROM Goods g").getResultList();
    }
    
    private Goods getGoodsFromDB(Goods goods) {
        Validate.notNull(emf,"Emf should be opende.");
        
        EntityManager em = emf.createEntityManager();
        return em.find(Goods.class,goods.getId());
    }
}
