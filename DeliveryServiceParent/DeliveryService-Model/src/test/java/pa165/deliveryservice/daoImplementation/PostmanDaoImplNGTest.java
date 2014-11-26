package pa165.deliveryservice.daoImplementation;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pa165.deliveryservice.DaoContext;
import pa165.deliveryservice.daoInterface.PostmanDao;
import pa165.deliveryservice.entity.Address;
import pa165.deliveryservice.entity.Customer;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.DeliveryStatus;
import pa165.deliveryservice.entity.Goods;
import pa165.deliveryservice.entity.Postman;

/**
 * Class is test for postman functions
 *
 * @author Pesava
 * @version 1.0
 */
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PostmanDaoImplNGTest extends AbstractTestNGSpringContextTests {

    public EntityManagerFactory emf;
    private EntityManager em;
    PostmanDao dao;
    private long postman1Id;

    //before each test
    @BeforeMethod
    public void setUpMethod() throws Exception {
        emf = Persistence.createEntityManagerFactory("myUnit");
        em = emf.createEntityManager();
        dao = new PostmanDaoImpl(em);

        em.getTransaction().begin();

        Address addr1 = new Address();
        Address addr2 = new Address();
        addr1.setCity("Brno");
        addr1.setStreet("Vojtkova 12");
        addr1.setPostcode(12345);
        addr2.setCity("Zidlochovice");
        addr2.setStreet("Vinohradska 87");
        addr2.setPostcode(54321);

        Goods g1 = new Goods();
        Goods g2 = new Goods();
        g1.setSeller("Tech House");
        g1.setPrice(1337);
        g2.setSeller("Toilets Inc.");
        g2.setPrice(1338);

        Postman pman1 = new Postman();
        pman1.setFirstName("Jiri");
        pman1.setLastName("Zbrozek");
        pman1.setDeliveries(new ArrayList<Delivery>());
        
        Customer cus1 = new Customer();
        Customer cus2 = new Customer();
        cus1.setFirstName("Milan");
        cus1.setLastName("Bochal");
        cus1.setAddress(addr1);
        cus1.setDeliveries(new ArrayList<Delivery>());
        cus2.setFirstName("Josef");
        cus2.setLastName("Majda");
        cus2.setAddress(addr2);
        cus2.setDeliveries(new ArrayList<Delivery>());

        Delivery del1 = new Delivery();
        Delivery del2 = new Delivery();
        del1.setName("DELx055");
        del1.setPostman(pman1);
        //TODO add deliveries
        del1.setCustomer(cus1);
        del1.setStatus(DeliveryStatus.INIT);
        del2.setName("DELx257");
        del2.setPostman(pman1);
        //TODO add goods to del2
        del2.setCustomer(cus2);
        del2.setStatus(DeliveryStatus.SENT);

        //set deliveries
        g1.setDelivery(del1);
        g2.setDelivery(del2);
        pman1.addDelivery(del1);
        pman1.addDelivery(del2);
        cus1.addDelivery(del1);
        cus2.addDelivery(del2);
        em.persist(g1);
        em.persist(g2);
        em.persist(pman1);
        em.persist(cus1);
        em.persist(cus2);
        em.persist(del1);
        em.persist(del2);

        postman1Id = pman1.getId();
        em.getTransaction().commit();
    }

    //after each test
    @AfterMethod
    public void tearDownMethod() throws Exception {
        emf.close();
        dao = null;
        postman1Id = Long.MIN_VALUE;
    }

    /**
     * Test of getAllPostmen method, of class PostmanDaoImpl.
     */
    @Test
    public void testGetAllPostmen() {
        List<Postman> postmen = dao.getAllPostmen();
        Assert.assertEquals(postmen.size(), 1);
    }

    /**
     * Test of updatePostman method, of class PostmanDaoImpl.
     */
    @Test
    public void testUpdatePostman() {
        Postman postmanDetached = em.find(Postman.class, postman1Id);
        em.detach(postmanDetached);

        postmanDetached.setFirstName("Karel");
        postmanDetached.setLastName("Prochazka");
        dao.updatePostman(postmanDetached);

        Postman postman = em.find(Postman.class, postman1Id);
        Assert.assertEquals(postman.getFirstName(), "Karel");
        Assert.assertEquals(postman.getLastName(), "Prochazka");
    }

    /**
     * Test of deletePostman method, of class PostmanDaoImpl.
     */
    @Test
    public void testDeletePostman() {
        Postman postman = em.find(Postman.class, postman1Id);
        em.getTransaction().begin();
        dao.deletePostman(postman);
        em.getTransaction().commit();

        List<Postman> postmans = em.createQuery("SELECT c FROM Postman c", Postman.class).getResultList();
        Assert.assertEquals(postmans.size(), 0, "Nothing deleted!");
//        Assert.assertEquals(postmans.get(0).getFirstName(), "Karel", "Deleted wrong customer!"); 
    }

    /**
     * Test of addPostman method, of class PostmanDaoImpl.
     */
    @Test
    public void testAddPostman() {
        Postman postman = new Postman();
        em.getTransaction().begin();
        dao.addPostman(postman);
        em.getTransaction().commit();

        List<Postman> postmen = em.createQuery("SELECT c FROM Postman c", Postman.class).getResultList();
        Assert.assertEquals(postmen.size(), 2, "Postman not added!");
    }
}
