package daoInterface;

import entity.Postman;
import java.util.List;

public interface PostmanDao {

    List<Postman> getAllPostman();

    void updatePostman();

    void deletePostman();

    void addPostman();

}
