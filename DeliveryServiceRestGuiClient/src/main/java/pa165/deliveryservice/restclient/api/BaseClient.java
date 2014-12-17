package pa165.deliveryservice.restclient.api;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Drimal
 */
public class BaseClient {
    private String baseUrl;
    
    /**
     * Constructor
     */
    public BaseClient()
    {
        // default URL
        this.baseUrl = "http://localhost:8080/rest";
    }

    public WebTarget getResource()
    {
        Client client = ClientBuilder.newClient();
        return client.target(baseUrl);
    }

    public String getBaseUrl()
    {
        return baseUrl;
    }

    public void setBaseUrl( String baseUrl )
    {
        this.baseUrl = baseUrl;
    }
}
