package pa165.deliveryservice.api;

import java.util.List;
import pa165.deliveryservice.api.dto.PostmanDto;

/**
 * Service layer Postman interface.
 * 
 * @author Martin Nekula
 */
public interface PostmanService {
    /**
     * Return list of all postmen in db
     * @return 
     */
    List<PostmanDto> getAllPostmen();

    /**
     * Update postman
     * @param postman 
     */
    void updatePostman(PostmanDto postman);

    /**
     * Delete postman
     * @param postman 
     */
    void deletePostman(PostmanDto postman);

    /**
     * Create postman and store it into db
     * @param postman 
     */
    void addPostman(PostmanDto postman);

    /**
     * Find postman by given id
     * @param id
     * @return PostmanDto with given id or null if there doesn't exists
     */
    public PostmanDto findPostman(long id);
}
