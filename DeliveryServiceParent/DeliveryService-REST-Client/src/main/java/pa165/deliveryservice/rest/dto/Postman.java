package pa165.deliveryservice.rest.dto;


/**
 *
 * @author Drimal
 */
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
}
