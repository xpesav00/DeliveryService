package pa165.deliveryservice.restclient;

import java.util.List;
import javax.ws.rs.core.Response;
import pa165.deliveryservice.rest.entity.Address;
import pa165.deliveryservice.rest.entity.Customer;
import pa165.deliveryservice.rest.entity.Postman;
import pa165.deliveryservice.restclient.api.CustomerClient;
import pa165.deliveryservice.restclient.api.PostmanClient;

/**
 * Testing method for REST implementation
 *
 * @author Drimal
 */
public class MainPage {
    
    public static final void main(String[] args){
        String host = "http://localhost:8080/pa165/rest";
    
        PostmanClient postmanClient = new PostmanClient(host);
        List<Postman> allPostmen = postmanClient.getAllPostmen();
        
        System.out.println("Get all postmen:");
        for (Postman postman : allPostmen) {
            System.out.println(postman);
        }
        
        Postman postman = postmanClient.getPostman(allPostmen.get(1).getId());
        System.out.println("\nGet postman: "+postman);
        
        postman = allPostmen.get(0);
        postman.setLastName("surname2");
        Response updateResponse = postmanClient.updatePostman(postman);
        System.out.println("After updated: "+postmanClient.getPostman(postman.getId()));

        Response deleteResponse = postmanClient.deletePostman(postman.getId());
        System.out.println("deleted response: "+deleteResponse.getStatus());
        
        Postman postman1 = new Postman();
        postman1.setFirstName("Anton");
        postman1.setLastName("Hostalek");
        Response createPostman = postmanClient.createPostman(postman1);
        System.out.println("created response: "+createPostman.getStatus());
        
        
        CustomerClient customerClient = new CustomerClient(host);
        List<Customer> allCustomers = customerClient.getAllCustomers();
        
        System.out.println("Get all customers:");
        for (Customer customer : allCustomers) {
            System.out.println(customer);
        }
        
        Customer customer = customerClient.getCustomer(allCustomers.get(0).getId());
        System.out.println("\nGet customer: "+customer);
        
        customer = allCustomers.get(0);
        customer.setLastName("surname2");
        updateResponse = customerClient.updateCustomer(customer);
        System.out.println("After updated: "+customerClient.getCustomer(customer.getId()));

        deleteResponse = customerClient.deleteCustomer(customer.getId());
        System.out.println("deleted response: "+deleteResponse.getStatus());
        
        Address address = new Address();
        address.setCity("Praha");
        address.setStreet("Hlavni");
        address.setPostcode(15698);

        Customer customer1 = new Customer();
        customer1.setFirstName("Anton");
        customer1.setLastName("Hostalek");
        customer1.setAddress(address);
        Response createResponse = customerClient.createCustomer(customer);
        System.out.println("created response: "+createResponse.getStatus());
    }
}
