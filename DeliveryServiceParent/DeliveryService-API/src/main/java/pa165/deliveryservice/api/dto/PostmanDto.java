package pa165.deliveryservice.api.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *  DTO for Postman entity.
 * 
 * @author Martin Nekula
 */
public class PostmanDto implements Cloneable{

    private long Id;
    private String firstName;
    private String lastName;
    @JsonIgnore
    private List<DeliveryDto> deliveries;
    
    public PostmanDto() {
        deliveries = new ArrayList<>();
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

    @JsonIgnore
    public List<DeliveryDto> getDeliveries() {
        return deliveries;
    }
    
    public void addDelivery(DeliveryDto delivery){
        this.deliveries.add(delivery);
    }

    public void setDeliveries(List<DeliveryDto> deliveries) {
        this.deliveries = deliveries;
    }

    public boolean areDeliveriesNull(){
        if(deliveries == null) return true;
        
        return false;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (this.Id ^ (this.Id >>> 32));
        hash = 67 * hash + Objects.hashCode(this.firstName);
        hash = 67 * hash + Objects.hashCode(this.lastName);
        hash = 67 * hash + Objects.hashCode(this.deliveries);
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
        if (this.Id != other.Id) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.deliveries, other.deliveries)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PostmanDto{" + "Id=" + Id + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }
           
}
