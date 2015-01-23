package pa165.deliveryservice.rest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import pa165.deliveryservice.api.UserService;
import pa165.deliveryservice.api.dto.UserDto;

/**
 *
 * @author Drimal
 */
public class RestAuthenticater {

    public void authenticateAndAuthorizeRemoteRestUser(SecurityContext context, HttpHeaders headers, UserService service){
        String[] split = encryptAuhrizationAttribute(headers).split(":");
        String username = split[0];
        String pswd = split[1];

        if(isUserSupported(service, username)){
            UserDto userDB = service.getUserByName(username);
            if(arePasswordsEquals(pswd, userDB)){
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, pswd);
                context.setAuthentication(token);
            }
        }
    }

    private String encryptAuhrizationAttribute(HttpHeaders headers){
        String authorizationToken = headers.get("authorization").get(0).split(" ")[1];
        byte[] parseBase64Binary = DatatypeConverter.parseBase64Binary(authorizationToken);
        String parsedToken=  "";
        try {
            parsedToken = new String(parseBase64Binary, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RestAuthenticater.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parsedToken;
    }

    private boolean isUserSupported(UserService service, String name){
        UserDto userByName = service.getUserByName(name);
        if(userByName != null){
            return true;
        }
        return false;
    }

    private boolean arePasswordsEquals(String password, UserDto user){
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte[] hash = digest.digest();
            if(Arrays.equals(hash, user.getPassword())){
                return true;
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RestAuthenticater.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
