package pa165.deliveryservice.restclient.interfaces;

import pa165.deliveryservice.api.dto.PostmanDto;
import org.codehaus.jackson.JsonNode;

/**
 *
 * @author Drimal
 */
public interface PostmanRestClient {
    public void create(PostmanDto postman);
    public void delete(String id);
    public void update(PostmanDto postman);
    public JsonNode findAll();
    public JsonNode findByID(String id);
}
