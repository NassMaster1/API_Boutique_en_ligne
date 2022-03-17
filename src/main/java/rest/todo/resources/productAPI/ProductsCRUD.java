package rest.todo.resources.productAPI;


import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import rest.todo.repository.ProductRepository;
import rest.todo.model.ProductModel;

/**
 * This class will map the resource to the URL products
 */
@Path("/products")
public class ProductsCRUD {
    /**
     * Allows inserting contextual objects into the class,
     * e.g. ServletContext, Request, Response, UriInfo
     */
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    ProductRepository productRepository = new ProductRepository();

    public ProductsCRUD() throws SQLException {
    }

    /**
     * This GET API :
     *
     * @return the list of products to the user in the browser
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductModel> getListProduct() {
        return productRepository.getListProduct();
    }



    @POST
    @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response PostProducts(@FormParam("brand") String brand,
                                 @FormParam("warding") String warding,
                                 @FormParam("price") float price,
                                 @FormParam("idCat") int idCat,
                                 @Context HttpServletResponse servletResponse) throws SQLException {

        ProductModel Product = new ProductModel(brand, warding, price, idCat);
        productRepository.postProduct(Product);
        return Response.status(200).entity("User post OK!").build();
    }

}