/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.servicelayer.serviceInterface;

import java.util.List;
import pa165.deliveryservice.entity.Address;
import pa165.deliveryservice.entity.Delivery;
import pa165.servicelayer.dto.CustomerDto;

/**
 *
 * @author Drimal
 */
public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    void updateCustomer(CustomerDto customer);

    void deleteCustomer(CustomerDto customer);

    void createCustomer(String firstName, String lastname, Address address, List<Delivery> deliveries);

    CustomerDto findCustomer(long id);
}
