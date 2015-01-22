package pa165.deliveryservice.restclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import pa165.deliveryservice.restclient.api.Authenticator;

/**
 * Parent rest client implementation
 *
 * @author Drimal
 */
public class BaseClient {
    protected static final String username = "rest";
    protected static String password = "rest";

    private String baseUrl;

    public BaseClient(String baseUrl) {
        this.baseUrl = "http://localhost:8080/pa165/rest";
    }

    public WebTarget getResource() {
        Client client = ClientBuilder.newClient().register(new Authenticator(username, password));
        return client.target(baseUrl);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
