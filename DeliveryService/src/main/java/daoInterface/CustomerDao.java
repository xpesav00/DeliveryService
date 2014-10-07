package daoInterface;

import entity.Customer;
import java.util.List;

public interface CustomerDao {

    List<Customer> getAllCustomer();

    void updateCustomer();

    void deleteCustomer();

    void addCustomer();

}
