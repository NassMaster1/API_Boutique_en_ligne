package rest.todo.resources.userAPI;
import rest.todo.model.UserModel;
import rest.todo.repository.UserRepository;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;

@Path("/User")
public class UserCRUD {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    UserRepository userRepository = new UserRepository();


    public UserCRUD() throws SQLException {
    }


    @GET
    @Path("{idUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserModel GetUser(@PathParam("idUser")int idUser) throws SQLException {
        return userRepository.GetUser(idUser);
    }

    @PUT
    @Path("{idUser}")
    @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED})
    public Response PutUser(@PathParam("idUser")int idUser,
                               @FormParam("firstName") String firstName,
                               @FormParam("lastName") String lastName,
                               @FormParam("email") String email,
                               @FormParam("password") String password,
                               @FormParam("role") String role,
                               @Context HttpServletResponse servletResponse) throws SQLException {
        UserModel user = new UserModel(firstName, lastName, email, password,role);
        int r=userRepository.putUser(idUser,user);
        if (r !=0)
            return Response.status(200).entity("User PUT OK!").build();
        else return Response.status(204).entity("User PUT not OK!").build();

    }


    @DELETE
    @Path("{idUser}")
    @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("idUser")int idUser) throws SQLException {
        int r=userRepository.DeleteUser(idUser);
        if (r !=0)
            return Response.status(200).entity("User DELETE OK!").build();
        else return Response.status(204).entity("User DELETE not OK!").build();
    }

    @POST
    @Path("/connexion")
    @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED})
    public boolean autentification(@FormParam("email") String email,
                            @FormParam("password") String password,
                            @Context HttpServletResponse servletResponse) throws SQLException {
        return userRepository.verifyAutentification(email,password);
    }

}
