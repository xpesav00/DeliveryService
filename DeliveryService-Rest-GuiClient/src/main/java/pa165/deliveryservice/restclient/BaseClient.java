package pa165.deliveryservice.restclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * Parent rest client implementation 
 *
 * @author Drimal
 */
public class BaseClient {
    
    private String baseUrl;

    public BaseClient(String baseUrl) {
        this.baseUrl = "http://localhost:8080/pa165/rest";
    }
    
    public WebTarget getResource() {
        Client client = ClientBuilder.newClient();
        return client.target(baseUrl);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
