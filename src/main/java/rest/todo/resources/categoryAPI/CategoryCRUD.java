package rest.todo.resources.categoryAPI;

import rest.todo.model.CategoryModel;
import rest.todo.repository.CategoryRepository;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;

@Path("/category")
public class CategoryCRUD {


    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    CategoryRepository categoryRepository = new CategoryRepository();

    public CategoryCRUD() throws SQLException {
    }


    @GET
    @Path("{idCategory}")
    @Produces(MediaType.APPLICATION_JSON)
    public CategoryModel GetUser(@PathParam("idCategory")int idCategory) throws SQLException {
        return categoryRepository.GetCategory(idCategory);
    }

    @PUT
    @Path("{idCategory}")
    @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED})
    public Response PutUser(@PathParam("idCategory")int idCategory,
                            @FormParam("nameC") String nameC,
                            @Context HttpServletResponse servletResponse) throws SQLException {
        CategoryModel category = new CategoryModel(nameC);
        int r=categoryRepository.putCategory(idCategory,category);
        if (r !=0)
            return Response.status(200).entity("User PUT OK!").build();
        else return Response.status(204).entity("User PUT not OK!").build();
    }

    @DELETE
    @Path("{idCategory}")
    @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("idCategory")int idCategory) throws SQLException {
        int r=categoryRepository.DeleteUser(idCategory);
        if (r !=0)
            return Response.status(200).entity("User DELETE OK!").build();
        else return Response.status(204).entity("User DELETE not OK!").build();
    }

}
