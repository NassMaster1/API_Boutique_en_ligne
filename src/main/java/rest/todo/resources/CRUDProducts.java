package rest.todo.resources;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import rest.todo.dao.DaoProduct;
import rest.todo.model.ProductModel;


/// Will map the resource to the URL todos
@Path("/products")
public class CRUDProducts {

    DaoProduct daoProduct = new DaoProduct();
    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    public CRUDProducts() throws SQLException {
    }

    // Return the list of todos to the user in the browser
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductModel> getListProduct() throws SQLException {

        return daoProduct.getListProduct();
    }

    //Todo (faire le post buggg)

    @POST
    @Produces("text/plain;charset=UTF-8")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response PostProducts(@FormParam("idProduct") int id,
                                 @FormParam("brand") String brand,
                                 @FormParam("warding") String warding,
                                 @FormParam("price") float price,
                                 @FormParam("idCat") int idCat,
                                 @Context HttpServletResponse servletResponse) throws IOException, SQLException {
        ProductModel Product =new ProductModel(id,brand,warding,price,idCat);
        daoProduct.putProduct(Product);
        return Response.status(200).entity("ok").build();
    }



/*

    // Return the list of todos for applications
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Todo> getTodos() {
        List<Todo> todos = new ArrayList<Todo>();
        todos.addAll(DaoProduct.instance.getModel().values());
        return todos;
    }

    // returns the number of todos
    // Use http://localhost:8080/com.vogella.jersey.todo/rest/todos/count
    // to get the total number of records
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = DaoProduct.instance.getModel().size();
        return String.valueOf(count);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newTodo(@FormParam("id") String id,
                        @FormParam("summary") String summary,
                        @FormParam("description") String description,
                        @Context HttpServletResponse servletResponse) throws IOException {
        Todo todo = new Todo(id, summary);
        if (description != null) {
            todo.setDescription(description);
        }
        DaoProduct.instance.getModel().put(id, todo);

        servletResponse.sendRedirect("../create_todo.html");
    }

    // Defines that the next path parameter after todos is
    // treated as a parameter and passed to the TodoResources
    // Allows to type http://localhost:8080/rest.todo/rest/todos/1
    // 1 will be treaded as parameter todo and passed to TodoResource
    @Path("{todo}")
    public TodoResource getTodo(@PathParam("todo") String id) {
        return new TodoResource(uriInfo, request, id);
    }
*/

}