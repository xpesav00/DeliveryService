package pa165.deliveryservice.daoImplementation;

import pa165.deliveryservice.daoInterface.CustomerDao;
import pa165.deliveryservice.entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class is implementation of interface CustomerDao and
 * describes real customer in delivery services
 * @author Pesava
 * @version 1.0
 */
public class CustomerDaoImpl implements CustomerDao{

        @PersistenceContext
        private EntityManager entityManager;
        private final static Logger log = LoggerFactory.getLogger(CustomerDaoImpl.class);
    
	public CustomerDaoImpl() {
	}
        
        public CustomerDaoImpl(EntityManager entityManager) {
            if(entityManager == null) {
                log.error("Internal Error: Param entityManger is null.");
                throw new NullPointerException("Internal Error: Param entityManger is null.");
            }
	}

	public List<Customer> getAllCustomer() {
		return entityManager.createQuery("SELECT d from Customer d",Customer.class).getResultList();
	}

	public void updateCustomer(Customer customer) {
            if(customer == null) {
                log.error("Internal Error: Param customer is null.");
                throw new NullPointerException("Internal Error: Param customer is null.");
            }
            
            if(customer.getId() == 0) {
                log.error("Internal Error: Param customer id is null.");
                throw new NullPointerException("Internal Error: Param customer id is null.");
            }
            
            //update
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();            
	}

	public void deleteCustomer(Long id) {
            if(id == null) {
                log.error("Internal Error: Param id is null.");
                throw new NullPointerException("Internal Error: Param id is null.");
            }
            
            if(id <= 0) {
                log.error("Internal Error: Param id is null.");
                throw new IllegalArgumentException("Internal Error: Param id equals or less then zero.");
            }
            
            Customer customer = entityManager.find(Customer.class, id);
            entityManager.remove(customer);
	}

	public void addCustomer(Customer customer) {
            if(customer == null) {
                log.error("Internal Error: Param customer is null.");
                throw new NullPointerException("Internal Error: Param customer is null.");
            }
            
            if(customer.getId() != 0) {
                log.error("Internal Error: Param customer id is setted.");
                throw new NullPointerException("Internal Error: Param customer id is setted.");
            }
            
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
	}

}
