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
        WebTarget postmanResource = this.getResource().path("/findAll");
        Invocation.Builder builder = postmanResource.request(MediaType.APPLICATION_JSON);
        return builder.get( new GenericType<List<Customer>>() {});
    }

    @Override
    public Customer getCustomer(long id) {
        WebTarget postmanResource = this.getResource().path(""+id);
        Invocation.Builder builder = postmanResource.request(MediaType.APPLICATION_JSON);
        return builder.get(Customer.class);
    }

    @Override
    public Response createCustomer(Customer customer) {
        WebTarget userResource = this.getResource().path("/create");
        Invocation.Builder builder = userResource.request(MediaType.APPLICATION_JSON);
        Entity<Customer> entity = Entity.entity(customer, MediaType.APPLICATION_JSON);
        return builder.post(entity);
    }

    @Override
    public Response deleteCustomer(long id) {
        WebTarget userResource = this.getResource().path("/delete/"+id);
        Invocation.Builder builder = userResource.request(MediaType.APPLICATION_JSON);
        return builder.delete();
    }

    @Override
    public Response updateCustomer(Customer customer) {
        WebTarget userResource = this.getResource().path("/update/"+customer.getId());
        Invocation.Builder builder = userResource.request();
        Entity<Customer> entity = Entity.entity(customer, MediaType.APPLICATION_JSON);
        return builder.put(entity);
    }
    
}
