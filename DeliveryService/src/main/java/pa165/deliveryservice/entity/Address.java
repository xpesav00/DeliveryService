package pa165.deliveryservice.entity;

import javax.persistence.*;

@Embeddable
public class Address {
    @Id
    @GeneratedValue
    private long Id;
    
    @Column
    private String city;
    
    @Column
    private String street;
    
    @Column
    private int postcode;

    public Address() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Address other = (Address) obj;
        if (this.Id != other.Id) {
            return false;
        }
        return true;
    }
        
    @Override
    public String toString(){
        return "Address: city: "+getCity()+", street: "+getStreet()+", postcode: "+getPostcode();
    }
}