package pa165.deliveryservice.api.dto;

import java.util.Objects;

/**
 * DTO for Address entity.
 *
 * @author Drimal
 */
public class AddressDto {
    private String city;
    private String street;
    private int postcode;

    public AddressDto() {
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
        hash = 67 * hash + Objects.hashCode(this.city);
        hash = 67 * hash + Objects.hashCode(this.street);
        hash = 67 * hash + this.postcode;
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
        final AddressDto other = (AddressDto) obj;
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (this.postcode != other.postcode) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return "Address [ city: "+city+", street: "+street+", postcode: "+postcode+"]";
    }
}