package pa165.deliveryservice.restclient.api;

import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pa165.deliveryservice.rest.interfaces.PostmanRestI;
import pa165.deliveryservice.restclient.BaseClient;
import pa165.deliveryservice.rest.entity.Postman;

/**
 * REST Postman client
 * @author Drimal
 */
public class PostmanClient extends BaseClient implements PostmanRestI{

    public PostmanClient(String baseUrl) {
        super(baseUrl);
        this.setBaseUrl(baseUrl + "/postman");
    }
 
    @Override
    public List<Postman> getAllPostmen(){
        WebTarget postmanResource = this.getResource().path("/findAll");
        Invocation.Builder builder = postmanResource.request(MediaType.APPLICATION_JSON);
        return builder.get( new GenericType<List<Postman>>() {});
    }
    
    @Override
    public Postman getPostman(long id){
        WebTarget postmanResource = this.getResource().path(""+id);
        Invocation.Builder builder = postmanResource.request(MediaType.APPLICATION_JSON);
        return builder.get(Postman.class);
    }
    
    @Override
    public Response createPostman(Postman postman)
    {
        WebTarget userResource = this.getResource().path("/create");
        Invocation.Builder builder = userResource.request(MediaType.APPLICATION_JSON);
        Entity<Postman> entity = Entity.entity(postman, MediaType.APPLICATION_JSON);
        return builder.post(entity);
    }
    
    @Override
    public Response deletePostman(long id)
    {
        WebTarget userResource = this.getResource().path("/delete/"+id);
        Invocation.Builder builder = userResource.request(MediaType.APPLICATION_JSON);
        return builder.delete();
    }

    @Override
    public Response updatePostman(Postman postman)
    {
        WebTarget userResource = this.getResource().path("/update/"+postman.getId());
        Invocation.Builder builder = userResource.request();
        Entity<Postman> entity = Entity.entity(postman, MediaType.APPLICATION_JSON);
        return builder.put(entity);
    }
}
