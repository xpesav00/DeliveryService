/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.servicelayer.serviceInterface;

import java.util.List;
import pa165.deliveryservice.entity.Address;
import pa165.deliveryservice.entity.Customer;
import pa165.deliveryservice.entity.Delivery;

/**
 *
 * @author Drimal
 */
public interface CustomerService {

    List<Customer> getAllCustomers();

    void updateCustomer(Customer customer);

    void deleteCustomer(Customer customer);

    void createCustomer(String firstName, String lastname, Address address, List<Delivery> deliveries);

    Customer findCustomer(long id);
}
