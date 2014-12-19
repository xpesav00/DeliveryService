package pa165.deliveryservice.rest.interfaces;

import java.util.List;
import javax.ws.rs.core.Response;
import pa165.deliveryservice.restclient.api.PostmanClient;
import pa165.deliveryservice.restclient.entity.Postman;


/**
 * REST API for client
 *
 * @author Drimal
 */
public interface PostmanRestI {
            
    List<Postman> getAllPostmen();
    
    Postman getPostman(long id);
    
    Response createPostman(Postman postman);
    
    Response deletePostman(long id);
   
    Response updatePostman(Postman postman);
}
