package pa165.deliveryservice.restclient;

import java.util.List;
import pa165.deliveryservice.restclient.api.PostmanClient;
import pa165.deliveryservice.restclient.entity.Postman;

/**
 *
 * @author Drimal
 */
public class MainPage {
    
    public static final void main(String[] args){
        String host = "http://localhost:8080/pa165/rest";
    
        PostmanClient postmanClient = new PostmanClient(host);
        List<Postman> allPostmen = postmanClient.getAllPostmen();
        
        for (Postman postman : allPostmen) {
            System.out.println("ID: "+postman.getId()+", first name: "+postman.getFirstName()+", last name: "+postman.getLastName());
        }
    }
}
