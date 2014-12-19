package pa165.deliveryservice.restclient;

import java.util.List;
import javax.ws.rs.core.Response;
import pa165.deliveryservice.restclient.api.PostmanClient;
import pa165.deliveryservice.restclient.entity.Postman;

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
        postman.setLastName("Before updated : "+postman);
        Response updateResponse = postmanClient.updatePostman(postman);
        System.out.println("After updated: "+postmanClient.getPostman(postman.getId()));

        Response deleteResponse = postmanClient.deletePostman(postman.getId());
        System.out.println("deleted response: "+updateResponse.getStatus());
        
        Postman postman1 = new Postman();
        postman1.setFirstName("Anton");
        postman1.setLastName("Hostalek");
        Response createPostman = postmanClient.createPostman(postman1);
        System.out.println("created response: "+createPostman.getStatus());
                
    }
}
