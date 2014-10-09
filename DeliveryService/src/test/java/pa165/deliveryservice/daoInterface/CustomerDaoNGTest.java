package pa165.deliveryservice.daoInterface;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pa165.deliveryservice.DaoContext;
import pa165.deliveryservice.entity.Customer;

/**
 *
 * @author Martin Nekula
 */
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerDaoNGTest extends AbstractTestNGSpringContextTests {

    public CustomerDaoNGTest() {
    }

    @PersistenceUnit
    public EntityManagerFactory emf;

    @BeforeMethod
    public void setUp() {
    }

    /**
     * Test of getAllCustomer method, of class CustomerDao.
     */
    @Test
    public void testGetAllCustomer() {
        System.out.println("getAllCustomer");
//        CustomerDao instance = new CustomerDaoImpl();
        List expResult = null;
//        List result = instance.getAllCustomer();
//        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateCustomer method, of class CustomerDao.
     */
    @Test
    public void testUpdateCustomer() {
        System.out.println("updateCustomer");
//        CustomerDao instance = new CustomerDaoImpl();
//        instance.updateCustomer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCustomer method, of class CustomerDao.
     */
    @Test
    public void testDeleteCustomer() {
        System.out.println("deleteCustomer");
//        CustomerDao instance = new CustomerDaoImpl();
//        instance.deleteCustomer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCustomer method, of class CustomerDao.
     */
    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        //      CustomerDao instance = new CustomerDaoImpl();
//        instance.addCustomer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
