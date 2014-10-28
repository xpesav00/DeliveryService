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
import pa165.deliveryservice.daoInterface.CustomerDao;
import pa165.deliveryservice.entity.Address;
import pa165.deliveryservice.entity.Customer;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.DeliveryStatus;
import pa165.deliveryservice.entity.Goods;
import pa165.deliveryservice.entity.Postman;

/**
 * Set of tests for Customer DAO Implementation.
 *
 * @author Martin Nekula
 */
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerDaoImplNGTest extends AbstractTestNGSpringContextTests {

    public EntityManagerFactory emf;
    private long cus1Id;

    //before each test
    @BeforeMethod
    public void setUpMethod() throws Exception {
        emf = Persistence.createEntityManagerFactory("myUnit");
        EntityManager em = emf.createEntityManager();
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
        //TODO add goods to del1
        del1.setCustomer(cus1);
        del1.setStatus(DeliveryStatus.INIT);
        del2.setName("DELx257");
        del2.setPostman(pman1);
        //TODO add goods to del2
        del2.setCustomer(cus2);
        del2.setStatus(DeliveryStatus.SENT);

        cus1.addDelivery(del1);
        cus2.addDelivery(del2);
        pman1.addDelivery(del1);
        pman1.addDelivery(del2);
        em.persist(g1);
        em.persist(g2);
        em.persist(pman1);
        em.persist(cus1);
        em.persist(cus2);
        em.persist(del1);
        em.persist(del2);

        cus1Id = cus1.getId();
        em.getTransaction().commit();
        em.close();
    }

    //after each test
    @AfterMethod
    public void tearDownMethod() throws Exception {
        if (emf != null) {
            emf.close();
        }
        cus1Id = Long.MIN_VALUE;
    }

    /**
     * Test of getAllCustomer method, of class CustomerDaoImpl.
     */
    @Test
    public void testGetAllCustomer() {
        CustomerDao custDao = new CustomerDaoImpl();
        List<Customer> customers = custDao.getAllCustomers();

        Assert.assertEquals(customers.size(), 2, "Not all customers in the list!");
    }

    /**
     * Test of updateCustomer method, of class CustomerDaoImpl.
     */
    @Test
    public void testUpdateCustomer() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Customer cusDetached = em.find(Customer.class, cus1Id);
        em.detach(cusDetached);
        em.getTransaction().commit();

        Address newAddr = new Address();
        newAddr.setCity("Novy Jicin");
        newAddr.setStreet("Bozetechova 90");
        newAddr.setPostcode(54345);

        cusDetached.setFirstName("Jozka");
        cusDetached.setLastName("Cerny");
        cusDetached.setAddress(newAddr);
        //TODO test deliveries update

        CustomerDao custDao = new CustomerDaoImpl();
        custDao.updateCustomer(cusDetached);

        Customer cusMerged = em.find(Customer.class, cus1Id);
        Assert.assertEquals(cusMerged.getFirstName(), "Jozka");
        Assert.assertEquals(cusMerged.getLastName(), "Cerny");
        Assert.assertEquals(cusMerged.getAddress().getCity(), "Novy Jicin");
        Assert.assertEquals(cusMerged.getAddress().getStreet(), "Bozetechova 90");
        Assert.assertEquals(cusMerged.getAddress().getPostcode(), 54345);

        em.close();
    }

    /**
     * Test of deleteCustomer method, of class CustomerDaoImpl.
     */
    @Test
    public void testDeleteCustomer() {
        EntityManager em = emf.createEntityManager();
        Customer cus1 = em.find(Customer.class, cus1Id);

        CustomerDao custDao = new CustomerDaoImpl();
        custDao.deleteCustomer(cus1);

        List<Customer> customers = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        Assert.assertEquals(customers.size(), 1, "Nothing deleted!");
        Assert.assertEquals(customers.get(0).getFirstName(), "Josef", "Deleted wrong customer!");
        em.close();
    }

    /**
     * Test of addCustomer method, of class CustomerDaoImpl.
     */
    @Test
    public void testAddCustomer() {
        Customer newCust = new Customer();
        newCust.setAddress(new Address());
        newCust.setFirstName("Jano");
        newCust.setLastName("Zoslovenska");
        newCust.setDeliveries(new ArrayList<Delivery>());
        CustomerDao custDao = new CustomerDaoImpl();
        custDao.addCustomer(newCust);

        EntityManager em = emf.createEntityManager();
        List<Customer> customers = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        Assert.assertEquals(customers.size(), 3, "Customer not added!");
        em.close();
    }

}
