package rest.todo.resources.CartAPI;


import rest.todo.model.CartModel;
import rest.todo.model.ProductModel;
import rest.todo.repository.CartRepository;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;

import java.util.Date;
import java.util.List;

@Path("/cart")
public class CartCRUD {
    CartRepository CartRepository = new CartRepository();
    // Allows inserting contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    public CartCRUD() throws SQLException {
    }


    @POST
    @Path("{idUser}/{idProduct}")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response PostProducts(@PathParam ("idUser") int idUser,@PathParam ("idProduct") int idProduct,@FormParam("quantite") int quantite  ) throws SQLException {
        Date date = new Date();

        long timeInMilliSeconds = date.getTime();
        java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);

        CartModel cart =new CartModel(idUser,date1,quantite);
        CartRepository.postCart(cart,idProduct);
        return Response.status(200).entity("Panier post OK!").build();
    }

  //Recuperer les id des produits d'un client
  @GET()
  @Path("/GetProduct_from_panier/{idUser}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<ProductModel> getListIdProduct_from_panier(@PathParam ("idUser") int idUser) throws SQLException {
      return CartRepository.getIdproduct_from_panier(idUser);
  }

    //Recuperer les id des produits d'un client
    @GET()
    @Path("/GetPanier/{idUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CartModel> GetPanier(@PathParam ("idUser") int idUser) throws SQLException {
        return CartRepository.GetPanier(idUser);
    }

}
