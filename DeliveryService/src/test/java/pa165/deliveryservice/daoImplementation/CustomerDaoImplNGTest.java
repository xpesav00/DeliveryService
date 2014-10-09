package pa165.deliveryservice.daoImplementation;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pa165.deliveryservice.DaoContext;

/**
 * Set of tests for Customer DAO Implementation.
 * 
 * @author Martin Nekula
 */
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerDaoImplNGTest extends AbstractTestNGSpringContextTests {

    public CustomerDaoImplNGTest() {
    }

    @PersistenceUnit
    public EntityManagerFactory emf;

    @BeforeMethod
    public void setUpMethod() throws Exception {
        emf = Persistence.createEntityManagerFactory("myUnit");
        
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        emf.close();
    }

    /**
     * Test of getAllCustomer method, of class CustomerDaoImpl.
     */
    @Test
    public void testGetAllCustomer() {
        System.out.println("getAllCustomer");
        CustomerDaoImpl instance = new CustomerDaoImpl();
        List expResult = null;
        List result = instance.getAllCustomer();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateCustomer method, of class CustomerDaoImpl.
     */
    @Test
    public void testUpdateCustomer() {
        System.out.println("updateCustomer");
        CustomerDaoImpl instance = new CustomerDaoImpl();
        instance.updateCustomer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCustomer method, of class CustomerDaoImpl.
     */
    @Test
    public void testDeleteCustomer() {
        System.out.println("deleteCustomer");
        CustomerDaoImpl instance = new CustomerDaoImpl();
        instance.deleteCustomer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCustomer method, of class CustomerDaoImpl.
     */
    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        CustomerDaoImpl instance = new CustomerDaoImpl();
        instance.addCustomer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
