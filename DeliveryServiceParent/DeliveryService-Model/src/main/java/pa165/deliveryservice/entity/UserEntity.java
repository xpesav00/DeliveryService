package pa165.deliveryservice.entity;

import java.util.Arrays;
import javax.persistence.*;

/**
 * Entity object represents user and his password
 *
 * @author Drimal
 */
@Entity
public class UserEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String username;

    @Column
    private byte[] password; //password is saved as hash of user password

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final UserEntity other = (UserEntity) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User[id: "+id+", name: "+username+", password: "+Arrays.toString(password)+"]";
    }
}
