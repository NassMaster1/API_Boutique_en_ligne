package rest.todo.resources.categoryAPI;
import rest.todo.model.CategoryModel;
import rest.todo.model.ProductModel;
import rest.todo.repository.CategoryRepository;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/categories")
public class CRUDCategory {
    CategoryRepository categoryRepository = new CategoryRepository();
    // Allows inserting contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    public CRUDCategory() throws SQLException {
    }

    // Return the list of category to the user in the browser
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryModel> getListCategories() {
        return categoryRepository.getModelList();
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response PostProducts(@FormParam("nameC") String nameC) throws SQLException {
        CategoryModel category =new CategoryModel(nameC);
        categoryRepository.putProduct(category);
        return Response.status(200).entity("Categories post OK!").build();
    }
}
