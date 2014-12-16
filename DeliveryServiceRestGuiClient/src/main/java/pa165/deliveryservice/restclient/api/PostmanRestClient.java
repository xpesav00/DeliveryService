package pa165.deliveryservice.restclient.api;

import pa165.deliveryservice.rest.dto.Postman;
import org.codehaus.jackson.JsonNode;

/**
 *
 * @author Drimal
 */
public interface PostmanRestClient {
    public void create(Postman postman);
    public void delete(String id);
    public void update(Postman postman);
    public JsonNode findAll();
    public JsonNode findByID(String id);
}
