package pa165.deliveryservice.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pa165.deliveryservice.api.PostmanService;
import pa165.deliveryservice.api.dto.PostmanDto;


/**
 * Rest controller for Postman
 * @author Drimal
 */
@RestController
@RequestMapping("/rest/postman")
public class PostmanRestController {
    @Autowired
    private PostmanService postmanService;
    
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<PostmanDto> findAll(){
        return postmanService.getAllPostmen();
    }
    
}