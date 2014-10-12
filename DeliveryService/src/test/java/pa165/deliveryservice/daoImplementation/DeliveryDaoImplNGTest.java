package pa165.deliveryservice.daoImplementation;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import org.apache.commons.lang3.Validate;
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

/**
 * Test of DeliveryImpl
 *
 * @author Martin Drimal
 */
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DeliveryDaoImplNGTest extends AbstractTestNGSpringContextTests {

    private EntityManagerFactory emf;
    private long deliverId;

    @BeforeMethod
    public void setUpMethod() throws Exception {
        emf = Persistence.createEntityManagerFactory("myUnit");
        prepareDatabase();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        if (emf != null) {
            emf.close();
        }
    }

    /**
     * Test of getAllDelivery method, of class DeliveryDaoImpl.
     */
    @Test
    public void testGetAllDeliveries() {
        System.out.println("getAllDeliveryTest");
        DeliveryDaoImpl instance = new DeliveryDaoImpl(emf);
        List expResult = retrieveDatabase();
        List<Delivery> result = instance.getAllDeliveries();
        assertEquals(result.size(), expResult.size(), "Number of row should be same.");
    }

    /**
     * Test of updateDelivery method, of class DeliveryDaoImpl.
     */
    @Test
    public void testUpdateDelivery() {
        System.out.println("updateDeliveryTest");
        DeliveryDaoImpl instance = new DeliveryDaoImpl(emf);
        Delivery del1 = new Delivery();
        del1.setName("Updating");
        instance.addDelivery(del1);
        
        Delivery delDb = getSpecificDelivery(del1);
        delDb.setName("Ondra");
        instance.updateDelivery(delDb);
        Delivery updated = getSpecificDelivery(delDb);

        assertEquals(updated.getName(), "Ondra", "Name should be changed");
    }

    /**
     * Test of deleteDelivery method, of class DeliveryDaoImpl.
     */
    @Test
    public void testDeleteDelivery() {
        System.out.println("deleteDelivery");
        DeliveryDaoImpl instance = new DeliveryDaoImpl(emf);
        Delivery delivery = new Delivery();
        delivery.setName("name");
        instance.addDelivery(delivery);
        int sizeBeforeDelete = getDatabaseSize();

        instance.deleteDelivery(delivery);
        List<Delivery> deliveryList = retrieveDatabase();
        assertEquals(retrieveDatabase().size(), sizeBeforeDelete - 1, "Database should be smaller.");
        assertFalse(deliveryList.contains(delivery));
    }

    /**
     * Test of addDelivery method, of class DeliveryDaoImpl.
     */
    @Test
    public void testAddDelivery() {
        System.out.println("addDelivery");
        DeliveryDaoImpl instance = new DeliveryDaoImpl();
        int sizeBeforeAdd = getDatabaseSize();
        Delivery delivery = createTestDelivery();
        delivery.setName("Delivery");
        instance.addDelivery(delivery);
        List<Delivery> deliveries = retrieveDatabase();
        Delivery actual = deliveries.get(getDatabaseSize() - 1);
        assertEquals(sizeBeforeAdd, deliveries.size(), "Database size should be larger");
        assertEquals(actual, delivery, "Deliveries should be equal.");
    }

    private int getDatabaseSize() {
        List<Delivery> deliveryList = retrieveDatabase();
        return deliveryList.size();
    }

    private void prepareDatabase() {
        EntityManager em = emf.createEntityManager();

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

        em.close();
    }

    private Delivery createTestDelivery() {
        Delivery deliveryOne = new Delivery();
        Customer cust = createTestCustomer("Pepa", "Pospisil");
        deliveryOne.setCustomer(cust);
        deliveryOne.setName("Test1");
        deliveryOne.setStatus(DeliveryStatus.INIT);
        return deliveryOne;
    }

    private List<Delivery> retrieveDatabase() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("Select d FROM Delivery d").getResultList();
    }

    /**
     * Create new test address
     *
     * @return
     */
    private Address createTestAddress() {
        Address address = new Address();
        address.setCity("Brno");
        address.setStreet("Hlavni 23");
        address.setPostcode(68501);

        return address;
    }

    /**
     * Create new customer
     *
     * @return return customer without set delivery list
     */
    private Customer createTestCustomer(String name, String surname) {
        Customer cust = new Customer();
        cust.setFirstName(name);
        cust.setLastName(surname);
        cust.setAddress(createTestAddress());

        return cust;
    }

    private Delivery getSpecificDelivery(Delivery delivery) {
        Validate.notNull(emf, "Emf should be opened.");

        EntityManager em = emf.createEntityManager();
        return em.find(Delivery.class, delivery.getId());
    }
}
