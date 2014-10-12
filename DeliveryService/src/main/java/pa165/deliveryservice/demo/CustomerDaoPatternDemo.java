package pa165.deliveryservice.demo;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pa165.deliveryservice.DaoContext;
import pa165.deliveryservice.daoImplementation.CustomerDaoImpl;
import pa165.deliveryservice.daoInterface.CustomerDao;
import pa165.deliveryservice.entity.Address;
import pa165.deliveryservice.entity.Customer;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.DeliveryStatus;

public class CustomerDaoPatternDemo {

     public static final void main(String[] args) throws SQLException {
         
         //add first customer
         Customer customer = new Customer();
         customer.setFirstName("Jan");
         customer.setLastName("Pesava");         
         //set address
         Address address = new Address();
         address.setCity("Brno");
         address.setPostcode(62300);
         address.setStreet("Karlova 56");
         customer.setAddress(address);
         //set delivery
         List<Delivery> deliveries = new ArrayList<>();
         Delivery delivery1 = new Delivery();
         delivery1.setName("Balicek");
         delivery1.setStatus(DeliveryStatus.INIT);
         delivery1.setCustomer(customer);
         deliveries.add(delivery1);
         customer.setDeliveries(deliveries);
         
         //get customer dao
         CustomerDao customerDao = new CustomerDaoImpl();
         
         //get empty customer list
         printAllCustomers(customerDao.getAllCustomers());
         
         //add deliveries
         customerDao.addCustomer(customer);
         
         //get all customers in database
         printAllCustomers(customerDao.getAllCustomers());
    }
     
     
     public static void printAllCustomers(List<Customer> customers){
        System.out.println("******************************");
        System.out.println("        LIST CUSTOMERS        ");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

}
