package pa165.deliveryservice.daoImplementation;

import pa165.deliveryservice.daoInterface.CustomerDao;
import pa165.deliveryservice.entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class is implementation of interface CustomerDao and describes real customer
 * in delivery services
 *
 * @author Jan Pesava
 * @version 1.0
 */
public class CustomerDaoImpl implements CustomerDao {

    private EntityManagerFactory emf;
    private final static Logger log = LoggerFactory.getLogger(CustomerDaoImpl.class);

    public CustomerDaoImpl() {
        emf = Persistence.createEntityManagerFactory("myUnit");
    }

    public CustomerDaoImpl(EntityManagerFactory emf) {
        if (emf == null) {
            log.error("Internal Error: Param entityManger is null.");
            throw new NullPointerException("Internal Error: Param entityManger is null.");
        }
        this.emf = emf;
    }

    /**
     * Finds a single customer in the DB.
     *
     * @param id Customers's ID.
     * @return Found customer.
     */
    public Customer getCustomer(Long id) {
        if (id == null && id < 0) {
            throw new IllegalArgumentException("Invalid id (id null or < 0).");
        }
        EntityManager em = emf.createEntityManager();
        Customer customer = em.find(Customer.class, id);
        em.close();
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();
        List<Customer> customers = em.createQuery("SELECT d from Customer d", Customer.class).getResultList();
        em.close();
        return customers;

    }

    @Override
    public void updateCustomer(Customer customer) {
        if (customer == null) {
            log.error("Internal Error: Param customer is null.");
            throw new NullPointerException("Internal Error: Param customer is null.");
        }

        if (customer.getId() == 0) {
            log.error("Internal Error: Param customer id is null.");
            throw new NullPointerException("Internal Error: Param customer id is null.");
        }

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(customer);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteCustomer(Customer customer) {
        if (customer == null) {
            log.error("Internal Error: Param customer is null.");
            throw new NullPointerException("Internal Error: Param customer is null.");
        }
        if (customer.getId() == 0) {
            log.error("Internal Error: Param customer id is null.");
            throw new NullPointerException("Internal Error: Param customer id is null.");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Customer customerdb = em.find(Customer.class, customer.getId());
        em.remove(customerdb);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void addCustomer(Customer customer) {
        if (customer == null) {
            log.error("Internal Error: Param customer is null.");
            throw new NullPointerException("Internal Error: Param customer is null.");
        }

        if (customer.getId() != 0) {
            log.error("Internal Error: Param customer id is set.");
            throw new NullPointerException("Internal Error: Param customer id is set.");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        em.close();
    }

}
