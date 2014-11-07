package pa165.deliveryservice.daoInterface;

import pa165.deliveryservice.entity.Customer;
import java.util.List;

/**
 * Performs necessary operations with customer entities.
 *
 * @author Jan Pešava
 */
public interface CustomerDao {

    /**
     * Retrieves all customers of the Delivery Service.
     *
     * @return List of customers.
     */
    List<Customer> getAllCustomers();

    /**
     * Updates information about a single customer.
     *
     * @param customer Customer to update in DB.
     */
    void updateCustomer(Customer customer);

    /**
     * Deletes a single customer from Delivery Service.
     *
     * @param customer Customer to delete from DB.
     */
    void deleteCustomer(Customer customer);

    /**
     * Adds one customer to Delivery Service.
     *
     * @param customer Customer to add to DB.
     */
    void addCustomer(Customer customer);

    /**
     * Finds a single customer in the DB.
     *
     * @param id Customers's ID.
     * @return Found customer.
     */
    Customer getCustomer(long id);

    void closeConnection();
}
