package pa165.deliveryservice.daoInterface;

import pa165.deliveryservice.entity.Postman;
import java.util.List;

public interface PostmanDao {

    List<Postman> getAllPostmen();

    void updatePostman(Postman postman);

    void deletePostman(Postman postman);

    void addPostman(Postman postman);

}
