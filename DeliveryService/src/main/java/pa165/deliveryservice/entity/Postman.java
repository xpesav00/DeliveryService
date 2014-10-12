package pa165.deliveryservice.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Represents a delivery service employee who takes deliveries to specified
 * address.
 * 
* @author Martin Nekula
 */
@Entity
public class Postman {

    @Id
    @GeneratedValue
    private long Id;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "postman")
    private List<Delivery> deliveries;

    public Postman() {
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

    public List<Delivery> getDeliveries() {
        return deliveries;
    }
    
    /**
     * Adds one delivery to postman.
     * 
     * @param delivery Delivery to be added.
     */
    public void addDelivery(Delivery delivery) {
        deliveries.add(delivery);
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int) (this.Id ^ (this.Id >>> 32));
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
        final Postman other = (Postman) obj;
        if (this.Id != other.Id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Postman{" + "Id=" + Id + ", firstName=" + firstName + ", lastName=" + lastName + ", deliveries=" + deliveries + '}';
    }
}