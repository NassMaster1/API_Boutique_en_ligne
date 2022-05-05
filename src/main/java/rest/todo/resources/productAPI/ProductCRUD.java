package rest.todo.resources.productAPI;


import rest.todo.model.ProductModel;
import rest.todo.repository.ProductRepository;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;


@Path("/product")
public class ProductCRUD {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    ProductRepository productRepository = new ProductRepository();

    public ProductCRUD() throws SQLException {
    }


    @GET
    @Path("{idProduct}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductModel GetProduct(@PathParam("idProduct")int idProduct) throws SQLException {
        return productRepository.GetProduct(idProduct);
    }

    @GET
    @Path("bycat/{idCat}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductModel> GetProductbycat(@PathParam("idCat")int idCat) throws SQLException {
        return productRepository.GetProductbycat(idCat);
    }

    @PUT
    @Path("{idProduct}")
    @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED})
    public Response putProduct(@PathParam("idProduct")int idProduct,
                               @FormParam("brand") String brand,
                               @FormParam("warding") String warding,
                               @FormParam("price") float price,
                               @FormParam("idCat") int idCat,
                               @Context HttpServletResponse servletResponse) throws SQLException {
        ProductModel Product = new ProductModel(brand, warding, price, idCat);
        int r=productRepository.putProduct(idProduct,Product);
        if (r !=0)
            return Response.status(200).entity("Products PUT OK!").build();
        else return Response.status(204).entity("Products PUT not OK!").build();

    }

    @DELETE
    @Path("{idProduct}")
    @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("idProduct")int idProduct) throws SQLException {
        int r=productRepository.DeleteProduct(idProduct);
        if (r !=0)
        return Response.status(200).entity("Products DELETE OK!").build();
        else return Response.status(204).entity("Products DELETE not OK!").build();
    }


}
