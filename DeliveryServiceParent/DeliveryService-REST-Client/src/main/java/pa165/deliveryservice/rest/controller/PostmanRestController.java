package pa165.deliveryservice.rest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pa165.deliveryservice.api.PostmanService;
import pa165.deliveryservice.api.dto.PostmanDto;
import pa165.deliveryservice.entity.Postman;


/**
 * Rest controller for Postman
 * @author Drimal
 */
@Controller("postmanRestController")
@RequestMapping("/rest/postman")
public class PostmanRestController {
    @Autowired
    private PostmanService postmanService;
    
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<PostmanDto> findAll(){
        return postmanService.getAllPostmen();
    }
    
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PostmanDto get(@PathVariable(value = "id") long id) {
        return postmanService.findPostman(id);
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody Postman postman) {
        PostmanDto postmanDto = convertPostmanToPostmanDto(postman);
        postmanService.addPostman(postmanDto);
    }

    @RequestMapping(value = "/update/{userId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void update(@PathVariable("userId") long id, @RequestBody Postman postman) {
        PostmanDto postmanDto = convertPostmanToPostmanDto(postman);
        postmanService.updatePostman(postmanDto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id){
        PostmanDto postman = postmanService.findPostman(id);
        postmanService.deletePostman(postman);
    }
    
    private PostmanDto convertPostmanToPostmanDto(Postman postman){
        PostmanDto postmanDto = new PostmanDto();
        postmanDto.setId(postman.getId());
        postmanDto.setFirstName(postman.getFirstName());
        postmanDto.setLastName(postman.getLastName());
        return postmanDto;
    }
}