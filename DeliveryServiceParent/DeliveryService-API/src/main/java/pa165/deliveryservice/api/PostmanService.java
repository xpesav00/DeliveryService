package pa165.deliveryservice.api;

import java.util.List;
import pa165.deliveryservice.api.dto.PostmanDto;

/**
 * Service layer Postman interface.
 * 
 * @author Martin Nekula
 */
public interface PostmanService {

    List<PostmanDto> getAllPostmen();

    void updatePostman(PostmanDto postman);

    void deletePostman(PostmanDto postman);

    void addPostman(PostmanDto postman);

    public PostmanDto findPostman(long id);
}
