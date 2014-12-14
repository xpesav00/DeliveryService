package pa165.deliveryservice.api;

import java.util.List;
import pa165.deliveryservice.entity.Address;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.api.dto.AddressDto;
import pa165.deliveryservice.api.dto.CustomerDto;
import pa165.deliveryservice.api.dto.DeliveryDto;

/**
 *
 * @author Drimal
 */
public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    void updateCustomer(CustomerDto customer);

    void deleteCustomer(CustomerDto customer);

    void createCustomer(String firstName, String lastname, AddressDto address, List<DeliveryDto> deliveries);

    CustomerDto findCustomer(long id);
    
    public AddressDto getCustomerAddress(long id);
}
