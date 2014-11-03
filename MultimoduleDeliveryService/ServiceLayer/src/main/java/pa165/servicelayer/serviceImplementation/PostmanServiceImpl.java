package pa165.servicelayer.serviceImplementation;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pa165.servicelayer.dto.PostmanDto;
import pa165.servicelayer.serviceInterface.PostmanService;

/**
 * Service layer for Postman entity.
 * 
 * @author Martin Nekula
 */
@Service
@Transactional
public class PostmanServiceImpl implements PostmanService{

    @Override
    public List<PostmanDto> getAllPostmen() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updatePostman(PostmanDto postman) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deletePostman(PostmanDto postman) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addPostman(PostmanDto postman) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PostmanDto getPostman(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
