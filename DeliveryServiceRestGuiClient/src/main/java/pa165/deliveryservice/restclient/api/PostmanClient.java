package pa165.deliveryservice.restclient.api;

import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pa165.deliveryservice.restclient.BaseClient;
import pa165.deliveryservice.restclient.entity.Postman;

/**
 *
 * @author Drimal
 */
public class PostmanClient extends BaseClient{

    public PostmanClient(String baseUrl) {
        super(baseUrl);
        this.setBaseUrl(baseUrl + "/postman");
    }
 
    public List<Postman> getAllPostmen(){
        WebTarget postmanResource = this.getResource().path("/findAll");
        Invocation.Builder builder = postmanResource.request(MediaType.APPLICATION_JSON);
        return builder.get( new GenericType<List<Postman>>() {});
    }
    
    public Postman getPostman(long id){
        WebTarget postmanResource = this.getResource().path("/get/"+id);
        Invocation.Builder builder = postmanResource.request(MediaType.APPLICATION_JSON);
        return builder.get(Postman.class);
    }
    
    public Response createPostman(Postman postman)
    {
        WebTarget userResource = this.getResource().path("/create");
        Invocation.Builder builder = userResource.request(MediaType.APPLICATION_JSON);
        Entity<Postman> entity = Entity.entity(postman, MediaType.APPLICATION_JSON);
        return builder.post(entity);
    }
    
    public Response deletePostman(long id)
    {
        WebTarget userResource = this.getResource().path("/delete/"+id);
        Invocation.Builder builder = userResource.request(MediaType.APPLICATION_JSON);
        return builder.delete();
    }

    public Response updateUser(Postman postman)
    {
        WebTarget userResource = this.getResource().path("/update/"+postman.getId());
        Invocation.Builder builder = userResource.request();
        Entity<Postman> entity = Entity.entity(postman, MediaType.APPLICATION_JSON);
        return builder.put(entity);
    }
}
