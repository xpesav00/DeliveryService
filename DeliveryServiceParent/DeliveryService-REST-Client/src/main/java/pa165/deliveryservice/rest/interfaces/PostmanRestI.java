//package pa165.deliveryservice.rest.interfaces;
//
//import java.util.List;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import pa165.deliveryservice.api.dto.PostmanDto;
//
//
///**
// * REST API for server
// *
// * @author Drimal
// */
//@Path("/rest/postman")
//public interface PostmanRestI {
//    
//    /**
//     * Method for retrieve all postmen
//     * @return list of postmen
//     */
//    @GET //Process HTTP GET request
//    @Path("/findAll")//relative uri path
//    @Produces(MediaType.APPLICATION_JSON)//type of content
//    public List<PostmanDto> findAll();
//    
//    /**
//     * Method for create specific postman
//     * @param postman postman to create
//     */
//    @POST
//    @Path("/create")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void create(PostmanDto postman);
//    
//    /**
//     * Method for find specific postman by ID
//     * @param id 
//     */
//    @POST
//    @Path("/findByID/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void findByID(@PathParam("id") String id);
//    
//    /**
//     * Method for update specific postman
//     * @param postman postman to update
//     */
//    @PUT
//    @Path("/update")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void update(PostmanDto postman);
//    
//    /**
//     * Method for delete specific postman
//     * @param id specify postman to delete
//     */
//    @DELETE
//    @Path("/delete/{id}")
//    public void delete(@PathParam("id") String id);
//}
