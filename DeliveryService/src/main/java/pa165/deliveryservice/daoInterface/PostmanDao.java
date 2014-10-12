package pa165.deliveryservice.daoInterface;

import pa165.deliveryservice.entity.Postman;
import java.util.List;

/**
 * Performs necessary operations with postman entities.
 * 
 * @author Martin Nekula
 */
public interface PostmanDao {

    /**
     * Retrieves all postmen currently employed at the Delivery Service.
     * 
     * @return List of postmen.
     */
    List<Postman> getAllPostmen();

    /**
     * Updates information about a single postman.
     * 
     * @param postman Postman to update in DB.
     */
    void updatePostman(Postman postman);

    /**
     * Deletes a single postman from Delivery Service.
     * 
     * @param postman Postman to delete from DB.
     */
    void deletePostman(Postman postman);

    /**
     * Adds one postman to Delivery Service.
     * 
     * @param postman Postman to add to DB.
     */
    void addPostman(Postman postman);

}
