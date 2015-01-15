package pa165.deliveryservice.rest.interfaces;

import java.util.List;
import javax.ws.rs.core.Response;
import pa165.deliveryservice.rest.entity.Customer;

/**
 * REST API for customer client
 *
 * @author Drimal
 */
public interface CustomerRestI {
    
    List<Customer> getAllCustomers();
    
    Customer getCustomer(long id);
    
    Response createCustomer(Customer customer);
    
    Response deleteCustomer(long id);
    
    Response updateCustomer(Customer customer);
}
