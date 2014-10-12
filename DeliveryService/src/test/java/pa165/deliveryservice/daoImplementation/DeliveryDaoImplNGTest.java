package pa165.deliveryservice.daoImplementation;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.commons.lang3.Validate;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pa165.deliveryservice.entity.Address;
import pa165.deliveryservice.entity.Customer;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.DeliveryStatus;

/**
 *
 * @author Drimal
 */
public class DeliveryDaoImplNGTest {
    EntityManagerFactory emf;

    @BeforeMethod
    public void setUpMethod() throws Exception {
        emf = Persistence.createEntityManagerFactory("myUnit");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        if(emf != null){
            emf.close();
        }
    }

    /**
     * Test of getAllDelivery method, of class DeliveryDaoImpl.
     */
    @Test
    public void testGetAllDelivery() {
        System.out.println("getAllDeliveryTest");
        prepareDatabase();
        
        DeliveryDaoImpl instance = new DeliveryDaoImpl();
        List expResult = retrieveDatabase();
        List result = instance.getAllDeliveries();
        assertEquals(result.size(), expResult.size(), "Number of row should be same.");
        assertEquals(result, expResult, "Retrieved databases should be same.");
    }

    /**
     * Test of updateDelivery method, of class DeliveryDaoImpl.
     */
    @Test
    public void testUpdateDelivery() {
        System.out.println("updateDeliveryTest");
        DeliveryDaoImpl instance = new DeliveryDaoImpl();
        Delivery del1 = new Delivery();
        del1.setId(Long.getLong("1"));
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
        DeliveryDaoImpl instance = new DeliveryDaoImpl();
        instance.deleteDelivery(null);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of addDelivery method, of class DeliveryDaoImpl.
     */
    @Test
    public void testAddDelivery() {
        System.out.println("addDelivery");
        DeliveryDaoImpl instance = new DeliveryDaoImpl();
        
        Delivery delivery = createTestDelivery();
        instance.addDelivery(delivery);
        List<Delivery> deliveries = retrieveDatabase();
        Delivery actual = deliveries.get(0);
        
        assertEquals(actual, delivery, "Deliveries should be equal.");        
    }
    
    private void prepareDatabase(){
        EntityManager em = emf.createEntityManager();
        
        Delivery deliveryOne = new Delivery();
        Customer cust = createTestCustomer("Pepa", "Pospisil");
        deliveryOne.setCustomer(cust);
        deliveryOne.setName("Test1");
        deliveryOne.setStatus(DeliveryStatus.INIT);
        
        Delivery deliveryTwo = new Delivery();
        deliveryTwo.setCustomer(createTestCustomer("Hozna", "Lamal"));
        deliveryTwo.setName("Test2");
        deliveryTwo.setStatus(DeliveryStatus.INIT);
        
        Delivery deliveryThird = new Delivery();
        deliveryThird.setCustomer(createTestCustomer("Karel", "Navratil"));
        deliveryThird.setName("Test3");
        deliveryTwo.setStatus(DeliveryStatus.INIT);
        
        em.getTransaction().begin();
        em.persist(deliveryOne);
        em.persist(deliveryTwo);
        em.persist(deliveryThird);
        em.getTransaction().commit();
        em.close();
    }
    
    private Delivery createTestDelivery(){
        Delivery deliveryOne = new Delivery();
        Customer cust = createTestCustomer("Pepa", "Pospisil");
        deliveryOne.setCustomer(cust);
        deliveryOne.setName("Test1");
        deliveryOne.setStatus(DeliveryStatus.INIT);
        return deliveryOne;
    }

    private List<Delivery> retrieveDatabase(){
        EntityManager em = emf.createEntityManager();
        return em.createQuery("Select d FROM Delivery d").getResultList();
    }

    /**
     * Create new test address
     * @return 
     */
    private Address createTestAddress(){   
        Address address = new Address();
        address.setCity("Brno");
        address.setStreet("Hlavni 23");
        address.setPostcode(68501);
        
        return address;
    }

    /**
     * Create new customer
     * @return return customer without set delivery list
     */
    private Customer createTestCustomer(String name, String surname){
        Customer cust = new Customer();
        cust.setFirstName(name);
        cust.setLastName(surname);
        cust.setAddress(createTestAddress());

        return cust;
    }
    
    private Delivery getSpecificDelivery(Delivery delivery){
        Validate.notNull(emf, "Emf should be opened.");

        EntityManager em = emf.createEntityManager();
        return em.find(Delivery.class, delivery.getId());
    }
}