package pa165.servicelayer.dto;

import java.util.List;

/**
 *  DTO for Postman entity.
 * 
 * @author Martin Nekula
 */
public class PostmanDto {

    private long Id;
    private String firstName;
    private String lastName;
    private List<DeliveryDto> deliveries;

    
    public PostmanDto() {
    }
    
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

    public List<DeliveryDto> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryDto> deliveries) {
        this.deliveries = deliveries;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + (int) (this.Id ^ (this.Id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PostmanDto other = (PostmanDto) obj;
        return this.Id == other.Id;
    }

    @Override
    public String toString() {
        return "PostmanDto{" + "Id=" + Id + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }
           
}
