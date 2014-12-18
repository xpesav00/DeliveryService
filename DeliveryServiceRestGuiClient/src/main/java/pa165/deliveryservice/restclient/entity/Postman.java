/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.deliveryservice.restclient.entity;

import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author Drimal
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
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
