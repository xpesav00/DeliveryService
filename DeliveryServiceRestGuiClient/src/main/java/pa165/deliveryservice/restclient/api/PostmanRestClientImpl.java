package pa165.deliveryservice.restclient.api;

import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import pa165.deliveryservice.rest.dto.Postman;

/**
 *
 * @author Drimal
 */
public class PostmanRestClientImpl extends RestClientAPI implements PostmanRestClient{
    private Logger logger = LoggerFactory.getLogger(PostmanRestClientImpl.class);
    
    public PostmanRestClientImpl(String url) {
        super(url);
    }

    @Override
    public void create(Postman postman) {
        logger.info("Creating entity Postman {}", postman);
        HttpEntity<Postman> entity = new HttpEntity<Postman>(postman,headers);
        uri =  rt.postForLocation(
        url.concat("create="),entity);
        logger.info("Entity created");
    }

    @Override
    public void delete(String id) {
        logger.info("Deleting entity Postman {}", id);
        rt.delete(url.concat("delete=").concat(id));
        logger.info("Entity deleted");
    }

    @Override
    public void update(Postman postman) {
        logger.info("Updating entity Postman {}", postman);
        rt.put(url.concat("update="),postman);
        logger.info("Entity updated");
    }

    @Override
    public JsonNode findAll() {
        logger.info("Retrieving all entity Postman");
        JsonNode jsonNode = rt.getForObject(url.concat("/findall"), JsonNode.class);
        for (JsonNode e : jsonNode) {//TOTO what to do with them
            System.out.println("Entity: "+e);
        }
        
        return jsonNode;
    }

    @Override
    public JsonNode findByID(String id) {
        logger.info("Finding entity Postman {}", id);
        return rt.getForObject(url.concat("findByID=").concat(id), JsonNode.class);
    }
    
}
