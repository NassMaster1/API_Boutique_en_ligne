package rest.todo.repository;


import rest.todo.model.CartModel;
import rest.todo.model.ProductModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ConnectDB.ConnectionDB.getStatement;

public class CartRepository {

    private final Statement stmt = getStatement();

    public CartRepository() throws SQLException {
    }


    public void postCart(CartModel cart,int idProduct) throws SQLException {
        int idPanier = 0;
        String query="INSERT INTO ws_rest.cart ( idUser, date, quantite) VALUES ("
                + cart.getIdUser() + ",'"
                + cart.getDate() + "',"
                + cart.getQuantite() + ") ";

       stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();
        int id = 0;
        while(rs.next()){
            idPanier = rs.getInt(1);
            System.out.println(id);
        }

       stmt.executeUpdate("INSERT INTO ws_rest.product_panier ( idProduct, idPanier,idUser) VALUES ("
                + idProduct + ","
               + idPanier + ","
               + cart.getIdUser() + ") ");
    }


    public List<ProductModel> getIdproduct_from_panier(int idUser) throws SQLException {
        ProductRepository repoProduct=new ProductRepository();
        List<ProductModel> modelList = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT idProduct FROM product_panier WHERE idUser="+idUser);
            modelList.clear();
            // Extract data from result set
            while (rs.next()) {
                int i= rs.getInt(1);
              modelList.add(repoProduct.GetProduct(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelList;
    }



    public List<CartModel> GetPanier(int idUser) {

        List<CartModel> modelList = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM cart WHERE idUser="+idUser);
            modelList.clear();
            // Extract data from result set
            while (rs.next()) {
                CartModel cart = new CartModel(rs.getInt("idPanier"),
                        rs.getInt("idUser"),
                        rs.getTime("date"),
                        rs.getInt("quantite"));
                modelList.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelList;

    }
}
