package pa165.deliveryservice.daoInterface;

import pa165.deliveryservice.entity.Postman;
import java.util.List;

public interface PostmanDao {

    List<Postman> getAllPostman();

    void updatePostman();

    void deletePostman();

    void addPostman();

}
