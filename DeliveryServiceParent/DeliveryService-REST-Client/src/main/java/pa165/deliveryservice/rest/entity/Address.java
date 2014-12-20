/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.deliveryservice.rest.entity;

/**
 *
 * @author Drimal
 */
public class Address {
    private String city;
    private String street;
    private int postcode;
    
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
    public String toString(){
        return "city: "+city+", street: " +street+", postcode: "+postcode;
    }
}
