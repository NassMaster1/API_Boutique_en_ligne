package rest.todo.resources.productAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import rest.todo.model.ProductModel;
import rest.todo.repository.ProductRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;
import java.sql.SQLException;

public class ProductCRUD {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    int id;
    ProductRepository productRepository = new ProductRepository();

    public ProductCRUD(UriInfo uriInfo, Request request, int id) throws SQLException {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED})
    public Response putProduct(JAXBElement<ProductModel> product) throws SQLException {
        return putAndGetResponse(product.getValue());
    }

    private Response putAndGetResponse(ProductModel product) throws SQLException {
        Response res;
        if (productRepository.getListProduct().contains(product)){
            productRepository.putProduct(product);
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        return res;
    }

    @DELETE
    public void deleteTodo() {
        boolean remove = productRepository.getListProduct().remove(productRepository.getProduct(id));
        if (!remove)
            throw new RuntimeException("Delete: product with " + id + " not found");
    }
}
