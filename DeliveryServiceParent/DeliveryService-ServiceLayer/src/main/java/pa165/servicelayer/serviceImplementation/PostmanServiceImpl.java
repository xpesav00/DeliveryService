package pa165.servicelayer.serviceImplementation;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pa165.deliveryservice.daoInterface.PostmanDao;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.entity.Postman;
import pa165.servicelayer.dto.DeliveryDto;
import pa165.servicelayer.dto.PostmanDto;
import pa165.servicelayer.serviceInterface.PostmanService;

/**
 * Service layer for Postman entity.
 *
 * @author Martin Nekula
 */
@Service
@Transactional
public class PostmanServiceImpl implements PostmanService {

    @Autowired
    private PostmanDao postmanDao;

    @Autowired
    private Mapper mapper;

    @Override
    public List<PostmanDto> getAllPostmen() {
        List<PostmanDto> postmenList = new ArrayList<>();
        for (Postman postman : postmanDao.getAllPostmen()) {
            postmenList.add(mapper.map(postman, PostmanDto.class));
        }
        return postmenList;
    }

    @Override
    public void updatePostman(PostmanDto postmanDto) {
        if (postmanDto == null) {
            throw new NullPointerException("Postman is null.");
        }

        Postman postman = postmanDao.getPostman(postmanDto.getId());
        postman.setFirstName(postmanDto.getFirstName());
        postman.setLastName(postmanDto.getLastName());

        List<Delivery> deliveries = new ArrayList<>();
        for (DeliveryDto deliveryDto : postmanDto.getDeliveries()) {
            deliveries.add(mapper.map(deliveryDto, Delivery.class));
        }

        postman.setDeliveries(deliveries);
        postmanDao.updatePostman(postman);
    }

    @Override
    public void deletePostman(PostmanDto postmanDto) {
        if (postmanDto == null) {
            throw new NullPointerException("Postman is null.");
        }
        
        Postman postman = postmanDao.getPostman(postmanDto.getId());
        postmanDao.deletePostman(postman);
    }

    @Override
    public void addPostman(PostmanDto postmanDto) {
        if (postmanDto == null) {
            throw new NullPointerException("Postman is null.");
        }
        
        Postman postman = mapper.map(postmanDto, Postman.class);
        postmanDao.addPostman(postman);
    }

    @Override
    public PostmanDto findPostman(long id) {
        Postman postman = postmanDao.getPostman(id);
        if (postman == null) {
            return null;
        }
        return mapper.map(postman, PostmanDto.class);
    }

    public void setPostmanDao(PostmanDao postmanDao) {
        this.postmanDao = postmanDao;
    }

}
