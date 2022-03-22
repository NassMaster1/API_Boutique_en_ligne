package rest.todo.resources.userAPI;

import rest.todo.model.UserModel;
import rest.todo.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/Users")
public class UsersCRUD {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    UserRepository userRepository = new UserRepository();

    public UsersCRUD() throws SQLException {
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserModel> getListUser() {
        return userRepository.getListUser();
    }

    @POST
    @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getListUser( @FormParam("firstName") String firstName,
                                 @FormParam("lastName") String lastName,
                                 @FormParam("email") String email,
                                 @FormParam("password") String password,
                                 @FormParam("role") String role,
                                 @Context HttpServletResponse servletResponse) throws SQLException {

        UserModel user = new UserModel(firstName, lastName, email, password,role);
        userRepository.postUser(user);
        return Response.status(200).entity("User post OK!").build();
    }
}
