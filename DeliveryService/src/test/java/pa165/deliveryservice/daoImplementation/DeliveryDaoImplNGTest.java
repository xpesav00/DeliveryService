package pa165.deliveryservice.daoImplementation;

import static com.oracle.nio.BufferSecrets.instance;
import static java.lang.StrictMath.exp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pa165.deliveryservice.DaoContext;
import pa165.deliveryservice.entity.Address;
import pa165.deliveryservice.entity.Customer;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.DeliveryStatus;
import pa165.deliveryservice.entity.Goods;

/**
 * Test of DeliveryImpl
 *
 * @author Martin Drimal
 */
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DeliveryDaoImplNGTest extends AbstractTestNGSpringContextTests {

    private static final Logger logger = LoggerFactory.getLogger(DeliveryDaoImplNGTest.class);
    private EntityManagerFactory emf;
    private EntityManager em;
    private long deliverId;
    private DeliveryDaoImpl instance;

    @BeforeMethod
    public void setUpMethod() throws Exception {
        emf = Persistence.createEntityManagerFactory("myUnit");
        em = emf.createEntityManager();
        instance = new DeliveryDaoImpl(em);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        em.close();
        emf.close();
    }

    /**
     * Test of addDelivery method, of class DeliveryDaoImpl.
     */
    @Test
    public void testAddDelivery() {
        logger.info("test add delivery");

        Delivery delivery = new Delivery();
        delivery.setName("Pokus");
        em.getTransaction().begin();
        instance.addDelivery(delivery);
        em.getTransaction().commit();
        Delivery deliveryFromDB = getSpecificDelivery(delivery);
        assertEquals(deliveryFromDB.getName(), "Pokus");
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testAddDeliveryWithNullArgument() {
        em.getTransaction().begin();
        instance.addDelivery(null);
        em.getTransaction().commit();
    }

    /**
     * Test of getAllDelivery method, of class DeliveryDaoImpl.
     */
    @Test
    public void testGetAllDeliveries() {
        logger.info("test get all deliveries");

        prepareDatabase();
        List expResult = retrieveDatabase();
        List<Delivery> result = instance.getAllDeliveries();
        assertEquals(result.size(), expResult.size(), "Number of row should be same.");
    }

    @Test
    public void testGetAllDeliveriesEmptyDatabase() {
        logger.info("test get all deliveries from empty database");

        List<Delivery> result = instance.getAllDeliveries();
        assertTrue(result.isEmpty(), "List should be empty.");
    }

    /**
     * Test of updateDelivery method, of class DeliveryDaoImpl.
     */
    @Test
    public void testUpdateDelivery() {
        logger.info("test update delivery");

        Delivery del1 = new Delivery();
        del1.setName("Updating");
        addDeliveryToDatabase(del1);

        Delivery delDb = getSpecificDelivery(del1);
        delDb.setName("Ondra");
        instance.updateDelivery(delDb);
        Delivery updated = getSpecificDelivery(delDb);

        assertEquals(updated.getName(), "Ondra", "Name should be changed");
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testUpdateDeliveryWithNullArgument() {
        logger.info("test update delivery with null argument");
        em.getTransaction().begin();
        instance.updateDelivery(null);
        em.getTransaction().commit();
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testUpdateDeliveryWithNegativeId() {
        logger.info("test update delivery with null argument");

        Delivery delivery = new Delivery();
        delivery.setId(-1);
        instance.updateDelivery(delivery);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testUpdateDeliveryWithWrongName() {
        logger.info("test update delivery with empty name");

        Delivery delivery = new Delivery();
        delivery.setName("");
        instance.updateDelivery(delivery);
    }

    /**
     * Test of deleteDelivery method, of class DeliveryDaoImpl.
     */
    @Test
    public void testDeleteDelivery() {
        logger.info("test delete delivery");

        Delivery delivery = new Delivery();
        delivery.setName("name");
        addDeliveryToDatabase(delivery);
        int sizeBeforeDelete = getDatabaseSize();

        em.getTransaction().begin();
        instance.deleteDelivery(delivery);
        em.getTransaction().commit();
        List<Delivery> deliveryList = retrieveDatabase();
        assertEquals(retrieveDatabase().size(), sizeBeforeDelete - 1, "Database should be smaller.");
        assertFalse(deliveryList.contains(delivery));
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testDeleteDeliveryTestWithNullArgument() {
        logger.info("test delete delivery with null argument");
        instance.deleteDelivery(null);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testDeleteDeliveryTestWithNegativeId() {
        logger.info("test delete delivery with null argument");

        Delivery delivery = new Delivery();
        delivery.setId(-1);
        instance.deleteDelivery(delivery);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testDeleteDeliveryTestWithEmptyName() {
        logger.info("test delete delivery with empty name");

        Delivery delivery = new Delivery();
        delivery.setName("");
        instance.deleteDelivery(delivery);
    }

    /**
     * Test of getDelivery method of class DeliveryDaoImpl
     */
    @Test
    public void testGetDelivery() {
        logger.info("test get delivery");

        Delivery delivery = new Delivery();
        delivery.setName("Delivery");
        addDeliveryToDatabase(delivery);

        Delivery deliveryFromDb = instance.getDelivery(delivery.getId());
        assertEquals(deliveryFromDb.getName(), "Delivery");
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testGetDeliveryWithNegativeArgument() {
        logger.info("test get delivery with negative argument");

        instance.getDelivery(-1);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testGetDeliveryWithZeroArgument() {
        logger.info("test get delivery with zero argument");

        instance.getDelivery(0);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class}, enabled = false)
    public void testGetDeliveryWithNonExistDelivery() {
        logger.info("test get delivery with non exist delivery");
        instance.getDelivery(3);
    }

    /**
     * Add delivery into database
     *
     * @param delivery
     */
    private void addDeliveryToDatabase(Delivery delivery) {
        em.getTransaction().begin();
        em.persist(delivery);
        em.getTransaction().commit();
    }

    /**
     * Get count of deliveries in database
     *
     * @return
     */
    private int getDatabaseSize() {
        List<Delivery> deliveryList = retrieveDatabase();
        return deliveryList.size();
    }

    /**
     * Prepare database for testing
     */
    private void prepareDatabase() {
        Delivery deliveryOne = new Delivery();
        Customer cust = createTestCustomer("Pepa", "Pospisil");
        deliveryOne.setCustomer(cust);
        deliveryOne.setName("Test1");
        deliveryOne.setStatus(DeliveryStatus.INIT);

        Delivery deliveryTwo = new Delivery();
        Customer cust2 = createTestCustomer("Hozna", "Lamal");
        deliveryTwo.setCustomer(cust2);
        deliveryTwo.setName("Test2");
        deliveryTwo.setStatus(DeliveryStatus.INIT);

        Delivery deliveryThird = new Delivery();
        Customer cust3 = createTestCustomer("Karel", "Navratil");
        deliveryThird.setCustomer(cust3);
        deliveryThird.setName("Test3");
        deliveryTwo.setStatus(DeliveryStatus.INIT);

        em.getTransaction().begin();
        em.persist(cust);
        em.persist(cust2);
        em.persist(cust3);
        em.persist(deliveryOne);
        em.persist(deliveryTwo);
        em.persist(deliveryThird);
        em.getTransaction().commit();
        deliverId = deliveryOne.getId();
    }

    /**
     * Create tested delivery
     *
     * @return created tested delivery
     */
    private Delivery createTestDelivery() {
        Delivery deliveryOne = new Delivery();
        Customer cust = createTestCustomer("Pepa", "Pospisil");
        deliveryOne.setCustomer(cust);
        deliveryOne.setName("Test1");
        List<Goods> goods = new ArrayList();
        goods.add(new Goods());
        deliveryOne.setGoods(goods);
        deliveryOne.setStatus(DeliveryStatus.INIT);
        return deliveryOne;
    }

    /**
     * Retrieve all deliveries from database
     *
     * @return retrieved deliveries from database
     */
    private List<Delivery> retrieveDatabase() {
        return em.createQuery("Select d FROM Delivery d").getResultList();
    }

    /**
     * Create new test address
     *
     * @return created address
     */
    private Address createTestAddress() {
        Address address = new Address();
        address.setCity("Brno");
        address.setStreet("Hlavni 23");
        address.setPostcode(68501);

        return address;
    }

    /**
     * Create new test customer
     *
     * @return customer without set delivery list
     */
    private Customer createTestCustomer(String name, String surname) {
        Customer cust = new Customer();
        cust.setFirstName(name);
        cust.setLastName(surname);
        cust.setAddress(createTestAddress());

        return cust;
    }

    /**
     * Retrieve specific delivery from database
     *
     * @return delivery from database
     */
    private Delivery getSpecificDelivery(Delivery delivery) {

        return em.find(Delivery.class, delivery.getId());
    }
}
