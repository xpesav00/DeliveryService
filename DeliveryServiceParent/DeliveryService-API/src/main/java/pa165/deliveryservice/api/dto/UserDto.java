package pa165.deliveryservice.api.dto;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Drimal
 */
public class UserDto {
    private String username;
    private byte[] password;

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
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.username);
        hash = 79 * hash + Arrays.hashCode(this.password);
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
        final UserDto other = (UserDto) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Arrays.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User[name: "+username+", password: "+Arrays.toString(password)+"]";
    }
}
