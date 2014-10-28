package pa165.deliveryservice.daoImplementation;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.commons.lang3.Validate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pa165.deliveryservice.DaoContext;
import pa165.deliveryservice.entity.*;

/**
 *  Test of GoodsDaoImpl
 * 
 * @author Jan Šťastný
 */

@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GoodsDaoImplNGTest extends AbstractTestNGSpringContextTests {
    private EntityManagerFactory emf;

    @BeforeMethod
    public void setUpMethod() throws Exception {
        emf = Persistence.createEntityManagerFactory("myUnit");
        builtDatabase();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        if(emf != null){
            emf.close();
        }
    }

    /**
     * Test of getAllGoods method, of class GoodsDaoImpl.
     */
    @Test
    public void testGetAllGoods() {
        System.out.println("getAllGoodsTest");
        builtDatabase();
        
        GoodsDaoImpl instance = new GoodsDaoImpl(emf);
        List expResult = retrieveDatabase();
        List result = instance.getAllGoods();
        assertEquals(result.size(), expResult.size(), "Number of row should be same.");
        assertEquals(result, expResult, "Retrieved databases should be same.");
    }

    /**
     * Test of updateGoods method, of class GoodsDaoImpl.
     */
    @Test
    public void testUpdateGoods() {
        System.out.println("updateGoodsTest");
        GoodsDaoImpl instance = new GoodsDaoImpl(emf);
        Goods goods = new Goods();
        instance.addGoods(goods);
//        goods.setId(Long.getLong("1"));
        Goods goodsFromDB = getSpecificGoods(goods);
        goodsFromDB.setPrice(150000);
        goodsFromDB.setSeller("ABC");
        instance.updateGoods(goodsFromDB);
        Goods updated = getSpecificGoods(goods);

        assertEquals(updated.getPrice(), 150000, "Prises should be equal.");
        assertEquals(updated.getSeller(), "ABC", "Sellers should be equal.");
    }

    /**
     * Test of deleteGoods method, of class GoodsDaoImpl.
     */
    @Test
    public void testDeleteGoods() {
        System.out.println("deleteGoods");
        GoodsDaoImpl instance = new GoodsDaoImpl(emf);
        
        Goods testingGoods = createTestingGoods();
        
        int sizeBeforeAdd = getSizeOfDTB(retrieveDatabase());
        
        System.out.println("Adding testing goods.");
        instance.addGoods(testingGoods);
        System.out.println("Deleting testing goods.");
        instance.deleteGoods(testingGoods);
        
        int sizeAfterDelete = getSizeOfDTB(retrieveDatabase());
        
        assertEquals(sizeBeforeAdd, sizeAfterDelete,"Database should contain same number of records.");
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of addGoods method, of class GoodsDaoImpl.
     */
    @Test
    public void testAddGoods() {
        System.out.println("addGoods");
        GoodsDaoImpl instance = new GoodsDaoImpl(emf);
        
        Goods testingGoods = createTestingGoods();        
        instance.addGoods(testingGoods);          
        long id = testingGoods.getId();
        
        Goods goodsFromDB = instance.getGoods(id);
        assertEquals(testingGoods, goodsFromDB, "Should be the same.");
    }
    
    private void builtDatabase(){
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
    
    private Goods createTestingGoods(){
        Goods goods = new Goods();
        goods.setPrice(1000000);
        goods.setSeller("AHOJ");
        return goods;
    }

    private List<Goods> retrieveDatabase(){
        EntityManager em = emf.createEntityManager();
        return em.createQuery("Select g FROM Goods g").getResultList();
    }
    
    private int getSizeOfDTB(List<Goods> list){
        return list.size();
    }

    private Goods getSpecificGoods(Goods goods){
        Validate.notNull(emf, "Emf should be opened.");

        EntityManager em = emf.createEntityManager();
        return em.find(Goods.class, goods.getId());
    }
}