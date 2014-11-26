package pa165.deliveryservice.daoImplementation;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pa165.deliveryservice.daoInterface.CustomerDao;
import pa165.deliveryservice.entity.Customer;
import pa165.deliveryservice.entity.Delivery;

/**
 * Class is implementation of interface CustomerDao and describes real customer
 * in delivery services
 *
 * @author Jan Pesava
 * @version 1.0
 */
@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

    private final static Logger log = LoggerFactory.getLogger(CustomerDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    public CustomerDaoImpl() {
        log.info("Create database connection.");
    }

    public CustomerDaoImpl(EntityManager em) {
        log.info("Create database connection.");
        if (em == null) {
            throw new NullPointerException("Internal Error: Param entityManger is null.");
        }
        this.em = em;
    }

    /**
     * Finds a single customer in the DB.
     *
     * @param id Customers's ID.
     * @return Found customer.
     */
    @Override
    public Customer getCustomer(long id) {
        log.info("Get specific customer from database.");
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid id (zero or negative).");
        }
        Customer customer = null;
        try {
            customer = em.find(Customer.class, id);
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("Unknow customer object.");
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        log.info("Retrieve all customers from database.");
        List<Customer> customers = em.createQuery("SELECT d from Customer d", Customer.class).getResultList();
        return customers;

    }

    @Override
    public void updateCustomer(Customer customer) {
        log.info("Update goods " + customer + " to database.");
        if (customer == null) {
            log.error("Internal Error: Param customer is null.");
            throw new NullPointerException("Internal Error: Param customer is null.");
        }

        if (customer.getId() == 0) {
            log.error("Internal Error: Param customer id is null.");
            throw new NullPointerException("Internal Error: Param customer id is null.");
        }
        em.merge(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        log.info("Delete goods " + customer + " from database.");
        if (customer == null) {
            log.error("Internal Error: Param customer is null.");
            throw new NullPointerException("Internal Error: Param customer is null.");
        }
        if (customer.getId() == 0) {
            log.error("Internal Error: Param customer id is null.");
            throw new NullPointerException("Internal Error: Param customer id is null.");
        }
        Customer customerDb = null;
        try {
            customerDb = em.find(Customer.class, customer.getId());
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("Unknown object to delete.");
        }
        //set 'no customer' to deleted customer's deliveries
        for (Delivery del : customerDb.getDeliveries()) {
            del.setCustomer(null);
        }
        em.remove(customerDb);
    }

    @Override
    public void addCustomer(Customer customer) {
        log.info("Add customer" + customer + " to database.");
        if (customer == null) {
            log.error("Internal Error: Param customer is null.");
            throw new NullPointerException("Internal Error: Param customer is null.");
        }

        if (customer.getId() != 0) {
            log.error("Internal Error: Param customer id is set.");
            throw new NullPointerException("Internal Error: Param customer id is set.");
        }
        em.persist(customer);
    }

    @Override
    public void closeConnection() {
        if (em != null) {
            em.close();
        }
    }
}
