package pa165.deliveryservice.daoInterface;

import pa165.deliveryservice.entity.Customer;
import java.util.List;

public interface CustomerDao {

    List<Customer> getAllCustomer();

    void updateCustomer(Customer customer);

    void deleteCustomer(Long id);

    void addCustomer(Customer customer);

}
