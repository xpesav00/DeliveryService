package pa165.deliveryservice.entity;

import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Represents a customer of delivery service
 * 
 * @author Martin Nekula
 */
public class Customer {

    @Id
    @GeneratedValue
    private long Id;
    
    private String firstName;
    
    private String lastName;
    
    private Address address;
    
    @ManyToOne(mappedBy = "customer")
    private List<Delivery> deliveries;

    public Customer() {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (int) (this.Id ^ (this.Id >>> 32));
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
        final Customer other = (Customer) obj;
        if (this.Id != other.Id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" + "Id=" + Id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", deliveries=" + deliveries + '}';
    }
}
