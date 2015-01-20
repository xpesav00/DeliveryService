package pa165.deliveryservice.entity;

import java.util.Arrays;
import javax.persistence.*;

/**
 * Entity object represents user and his password
 *
 * @author Drimal
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String username;

    @Column
    private byte[] password; //password is saved as hash of user password

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User[id: "+id+", name: "+username+", password: "+Arrays.toString(password)+"]";
    }
}
