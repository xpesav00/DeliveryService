package pa165.deliveryservice.restclient.api;

import java.util.Collections;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pa165.deliveryservice.rest.entity.Customer;
import pa165.deliveryservice.rest.entity.Postman;
import pa165.deliveryservice.rest.interfaces.CustomerRestApi;
import pa165.deliveryservice.restclient.BaseClient;

/**
 * REST Customer client
 *
 * @author Drimal
 */
public class CustomerClient extends BaseClient implements CustomerRestApi{

    public CustomerClient(String baseUrl) {
        super(baseUrl);
        this.setBaseUrl(baseUrl + "/customer");
    }

    @Override
    public List<Customer> getAllCustomers() throws RuntimeException {
        WebTarget customerResource = this.getResource().path("/findAll");
        Invocation.Builder builder = customerResource.request(MediaType.APPLICATION_JSON);
        List<Customer> result = Collections.EMPTY_LIST;
        try{
                result = builder.get( new GenericType<List<Customer>>() {});
        }catch(Exception ex){
            throw new RuntimeException("Can't retrieve all customers!", ex);
        }
        return result;
    }

    @Override
    public Customer getCustomer(long id) throws RuntimeException {
        WebTarget customerResource = this.getResource().path(""+id);
        Invocation.Builder builder = customerResource.request(MediaType.APPLICATION_JSON);
        Customer result = null;
        try{
            result = builder.get(Customer.class);
        }catch(Exception ex){
            throw new RuntimeException("Can't retrieve customer!", ex);
        }
        return result;
    }

    @Override
    public Response createCustomer(Customer customer) throws RuntimeException{
        WebTarget customerResource = this.getResource().path("/create");
        Invocation.Builder builder = customerResource.request(MediaType.APPLICATION_JSON);
        Entity<Customer> entity = Entity.entity(customer, MediaType.APPLICATION_JSON);
        Response response = null;
        try{    
            response = builder.post(entity);
        }catch(Exception ex){
            throw new RuntimeException("Can't create customer!", ex);
        }
        return response;
    }

    @Override
    public Response deleteCustomer(long id) throws RuntimeException{
        WebTarget customerResource = this.getResource().path("/delete/"+id);
        Invocation.Builder builder = customerResource.request(MediaType.APPLICATION_JSON);
        Response response = null;
        try{    
            response = builder.delete();
        }catch(Exception ex){
            throw new RuntimeException("Can't delete customer!", ex);
        }
        return response;        
    }

    @Override
    public Response updateCustomer(Customer customer) {
        WebTarget customerResource = this.getResource().path("/update/"+customer.getId());
        Invocation.Builder builder = customerResource.request();
        Entity<Customer> entity = Entity.entity(customer, MediaType.APPLICATION_JSON);
        Response response = null;
        try{    
            response = builder.put(entity);
        }catch(Exception ex){
            throw new RuntimeException("Can't update customer!", ex);
        }
        return response;  
    }
    
}
