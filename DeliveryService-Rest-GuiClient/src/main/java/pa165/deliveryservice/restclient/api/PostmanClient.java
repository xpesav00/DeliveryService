package pa165.deliveryservice.restclient.api;

import java.util.Collections;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pa165.deliveryservice.rest.interfaces.PostmanRestApi;
import pa165.deliveryservice.restclient.BaseClient;
import pa165.deliveryservice.rest.entity.Postman;

/**
 * REST Postman client
 * @author Drimal
 */
public class PostmanClient extends BaseClient implements PostmanRestApi{

    public PostmanClient(String baseUrl) {
        super(baseUrl);
        this.setBaseUrl(baseUrl + "/postman");
    }
 
    @Override
    public List<Postman> getAllPostmen() throws RuntimeException
    {
        WebTarget postmanResource = this.getResource().path("/findAll");
        Invocation.Builder builder = postmanResource.request(MediaType.APPLICATION_JSON);
        List<Postman> result = Collections.EMPTY_LIST;
        try{
                result = builder.get( new GenericType<List<Postman>>() {});
        }catch(Exception ex){
            throw new RuntimeException("Can't retrieve all postmen!", ex);
        }
        return result;
    }
    
    @Override
    public Postman getPostman(long id) throws RuntimeException
    {
        WebTarget postmanResource = this.getResource().path(""+id);
        Invocation.Builder builder = postmanResource.request(MediaType.APPLICATION_JSON);
        Postman result = null;
        try{
            result = builder.get(Postman.class);
        }catch(Exception ex){
            throw new RuntimeException("Can't retrieve postman!", ex);
        }
        return result;
    }
    
    @Override
    public Response createPostman(Postman postman) throws RuntimeException
    {
        WebTarget userResource = this.getResource().path("/create");
        Invocation.Builder builder = userResource.request(MediaType.APPLICATION_JSON);
        Entity<Postman> entity = Entity.entity(postman, MediaType.APPLICATION_JSON);
        try{
            return builder.post(entity);
        }catch(Exception ex){
            throw new RuntimeException("Can't create postman!", ex);    
        }
    }
    
    @Override
    public Response deletePostman(long id) throws RuntimeException
    {
        WebTarget userResource = this.getResource().path("/delete/"+id);
        Invocation.Builder builder = userResource.request(MediaType.APPLICATION_JSON);
        try{
            return builder.delete();
        }catch(Exception ex){
            throw new RuntimeException("Can't delete postman!", ex);    
        }
    }

    @Override
    public Response updatePostman(Postman postman) throws RuntimeException
    {
        WebTarget userResource = this.getResource().path("/update/"+postman.getId());
        Invocation.Builder builder = userResource.request();
        Entity<Postman> entity = Entity.entity(postman, MediaType.APPLICATION_JSON);
        try{
            return builder.put(entity);
        }catch(Exception ex){
            throw new RuntimeException("Can't update postman!", ex);    
        }
    }
}
