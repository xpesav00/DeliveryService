package pa165.deliveryservice.api;

import java.util.List;
import pa165.deliveryservice.api.dto.AddressDto;
import pa165.deliveryservice.api.dto.CustomerDto;
import pa165.deliveryservice.api.dto.DeliveryDto;

/**
 * Service layer Customer interface.
 * @author Drimal
 */
public interface CustomerService {

    /**
     * Returns list of all customers in db
     * @return list of customers
     */
    List<CustomerDto> getAllCustomers();

    /**
     * Updates customer
     * @param customer
    */
    void updateCustomer(CustomerDto customer);

    /**
     * Deletes customer
     * @param customer 
     */
    void deleteCustomer(CustomerDto customer);

    /**
     * Create customer and store it into db
     * @param firstName name of customer
     * @param lastname surname of customer
     * @param address customer's address
     * @param deliveries customer's deliveries
     */
    void createCustomer(String firstName, String lastname, AddressDto address, List<DeliveryDto> deliveries);

    /**
     * Find customer by given id.
     * @param id id of delivery
     * @return CustomerDto with given id or null if there doesn't exists.
     * @throws IllegalArgumentException
     */
    CustomerDto findCustomer(long id);
    
    public AddressDto getCustomerAddress(long id);
}
