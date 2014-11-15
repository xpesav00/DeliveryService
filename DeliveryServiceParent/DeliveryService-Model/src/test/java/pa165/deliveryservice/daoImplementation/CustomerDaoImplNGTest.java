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

    private EntityManagerFactory emf;
    private EntityManager em;
    private long cus1Id;

    @BeforeMethod
    public void setUpMethod() throws Exception {
        emf = Persistence.createEntityManagerFactory("myUnit");
        em = emf.createEntityManager();
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
        em.getTransaction().commit();
        cus1Id = cus1.getId();
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
        CustomerDao custDao = new CustomerDaoImpl(em);
        List<Customer> customers = custDao.getAllCustomers();

        Assert.assertEquals(customers.size(), 2, "Not all customers in the list!");
    }

    /**
     * Test of updateCustomer method, of class CustomerDaoImpl.
     */
    @Test
    public void testUpdateCustomer() {

        em.getTransaction().begin();
        Customer customer = new Customer();
        customer.setFirstName("FirstName");
        customer.setFirstName("LastName");
        Address newAddr = new Address();
        newAddr.setCity("Novy Jicin");
        newAddr.setStreet("Bozetechova 90");
        newAddr.setPostcode(54345);
        customer.setAddress(newAddr);
        em.persist(customer);

        Customer updated = em.find(Customer.class, customer.getId());
        em.detach(updated);
        newAddr.setCity("Olomouc");
        newAddr.setStreet("Bozetechova 90");
        newAddr.setPostcode(54345);

        em.getTransaction().commit();

        updated.setFirstName("Jozka");
        updated.setLastName("Cerny");
        updated.setAddress(newAddr);

        CustomerDao custDao = new CustomerDaoImpl(em);
        em.getTransaction().begin();
        custDao.updateCustomer(updated);
        em.getTransaction().commit();

        Customer cusMerged = em.find(Customer.class, updated.getId());
        Assert.assertEquals(cusMerged.getFirstName(), "Jozka");
        Assert.assertEquals(cusMerged.getLastName(), "Cerny");
        Assert.assertEquals(cusMerged.getAddress().getCity(), "Olomouc");
        Assert.assertEquals(cusMerged.getAddress().getStreet(), "Bozetechova 90");
        Assert.assertEquals(cusMerged.getAddress().getPostcode(), 54345);
    }

    /**
     * Test of deleteCustomer method, of class CustomerDaoImpl.
     */
    @Test
    public void testDeleteCustomer() {
        em.getTransaction().begin();
        Customer customer = new Customer();
        customer.setFirstName("Petr");
        customer.setLastName("Chuchvalec");
        customer.setAddress(new Address());
        customer.setDeliveries(new ArrayList<Delivery>());
        em.persist(customer);
        em.getTransaction().commit();

        CustomerDao custDao = new CustomerDaoImpl(em);
        em.getTransaction().begin();
        custDao.deleteCustomer(customer);
        em.getTransaction().commit();

        List<Customer> customers = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        Assert.assertEquals(customers.size(), 2, "Nothing deleted!");
        Assert.assertEquals(customers.get(0).getFirstName(), "Milan", "Deleted wrong customer!");
        Assert.assertEquals(customers.get(1).getFirstName(), "Josef", "Deleted wrong customer!");
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
        CustomerDao custDao = new CustomerDaoImpl(em);
        em.getTransaction().begin();
        custDao.addCustomer(newCust);
        em.getTransaction().commit();

        em.getTransaction().begin();
        List<Customer> customers = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        em.getTransaction().commit();
        Assert.assertEquals(customers.size(), 3, "Customer not added!");
    }

}
