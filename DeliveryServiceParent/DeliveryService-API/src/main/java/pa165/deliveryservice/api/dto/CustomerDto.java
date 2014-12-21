package pa165.deliveryservice.api.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO for Customer entity.
 *
 * @author Martin Nekula
 */
public class CustomerDto implements Cloneable{

    private long Id;
    private String firstName;
    private String lastName;
    private AddressDto address;
    private List<DeliveryDto> deliveries = new ArrayList<>();

    
    public CustomerDto() {
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

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public List<DeliveryDto> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryDto> deliveries) {
        this.deliveries = deliveries;
    }
    
    public void addDelivery(DeliveryDto delivery){
        this.deliveries.add(delivery);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.Id ^ (this.Id >>> 32));
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
        final CustomerDto other = (CustomerDto) obj;
        if (this.Id != other.Id) {
            return false;
        }
        return true;
    }   

    @Override
    public String toString() {
        return "CustomerDto{" + "Id=" + Id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + '}';
    }
    
}
