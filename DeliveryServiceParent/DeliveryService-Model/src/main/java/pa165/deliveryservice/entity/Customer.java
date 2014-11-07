package pa165.deliveryservice.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Represents a customer of delivery service
 *
 * @author Jan Pešava
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private long Id;

    private String firstName;

    private String lastName;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
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

    /**
     * Adds one delivery to customer.
     *
     * @param delivery Delivery to be added.
     */
    public void addDelivery(Delivery delivery) {
        deliveries.add(delivery);
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
