package rest.todo.repository;

import rest.todo.model.ProductModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ConnectDB.ConnectionDB.getStatement;


public class ProductRepository {

    private final List<ProductModel> ListProduct;
    private final Statement stmt = getStatement();

    public ProductRepository() throws SQLException {
        this.ListProduct=new ArrayList<>();
    }

    public List<ProductModel> getListProduct() {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM product");
            // Extract data from result set
            while (rs.next()) {
                ListProduct.add(new ProductModel(rs.getInt("idProduct"),
                        rs.getString("brand"),
                        rs.getString("warding"),
                        rs.getFloat("price"),
                        rs.getInt("idCat")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListProduct;
    }

    public void putProduct(ProductModel product) throws SQLException {
         stmt.executeUpdate("INSERT INTO ws_rest.product ( brand, warding, price, idCat) VALUES ('"
                 +product.getBrand()+"','"
                 +product.getWarding()+"',"
                 +product.getPrice()+","
                 +product.getIdCat() +") ");
    }
}