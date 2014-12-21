package pa165.deliveryservice.restclient.api;

import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pa165.deliveryservice.rest.entity.Customer;
import pa165.deliveryservice.rest.interfaces.CustomerRestI;
import pa165.deliveryservice.restclient.BaseClient;

/**
 * REST Customer client
 *
 * @author Drimal
 */
public class CustomerClient extends BaseClient implements CustomerRestI{

    public CustomerClient(String baseUrl) {
        super(baseUrl);
        this.setBaseUrl(baseUrl + "/customer");
    }

    @Override
    public List<Customer> getAllCustomers() {
        WebTarget customerResource = this.getResource().path("/findAll");
        Invocation.Builder builder = customerResource.request(MediaType.APPLICATION_JSON);
        return builder.get( new GenericType<List<Customer>>() {});
    }

    @Override
    public Customer getCustomer(long id) {
        WebTarget customerResource = this.getResource().path(""+id);
        Invocation.Builder builder = customerResource.request(MediaType.APPLICATION_JSON);
        return builder.get(Customer.class);
    }

    @Override
    public Response createCustomer(Customer customer) {
        WebTarget customerResource = this.getResource().path("/create");
        Invocation.Builder builder = customerResource.request(MediaType.APPLICATION_JSON);
        Entity<Customer> entity = Entity.entity(customer, MediaType.APPLICATION_JSON);
        return builder.post(entity);
    }

    @Override
    public Response deleteCustomer(long id) {
        WebTarget customerResource = this.getResource().path("/delete/"+id);
        Invocation.Builder builder = customerResource.request(MediaType.APPLICATION_JSON);
        return builder.delete();
    }

    @Override
    public Response updateCustomer(Customer customer) {
        WebTarget customerResource = this.getResource().path("/update/"+customer.getId());
        Invocation.Builder builder = customerResource.request();
        Entity<Customer> entity = Entity.entity(customer, MediaType.APPLICATION_JSON);
        return builder.put(entity);
    }
    
}
