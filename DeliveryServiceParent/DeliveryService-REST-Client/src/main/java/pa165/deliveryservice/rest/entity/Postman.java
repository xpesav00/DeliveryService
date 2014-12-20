package pa165.deliveryservice.rest.entity;

import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * DTO object for REST client
 *
 * @author Drimal
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Postman {

    private long Id;
    private String firstName;
    private String lastName;

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Override
    public String toString(){
        return "ID: "+getId()+", first name: "+getFirstName()+", last name: "+getLastName();
    }
    
}
