package pa165.deliveryservice.daoInterface;

import pa165.deliveryservice.entity.Customer;
import java.util.List;

public interface CustomerDao {

    List<Customer> getAllCustomer();

    void updateCustomer();

    void deleteCustomer();

    void addCustomer();

}
