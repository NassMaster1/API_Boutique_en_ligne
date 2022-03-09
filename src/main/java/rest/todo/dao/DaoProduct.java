package rest.todo.dao;

import ConnectDB.ConnectionDB;
import rest.todo.model.ProductModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ConnectDB.ConnectionDB.getStatement;


public class DaoProduct {

    private List<ProductModel> ListProduct = null;
    private final Statement stmt = getStatement();

     public DaoProduct() throws SQLException {
         this.ListProduct=new ArrayList<ProductModel>();
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
            e.getMessage();
            e.printStackTrace();
        }
        return ListProduct;
    }

    public void putProduct(ProductModel Porduct) throws SQLException {
         Porduct.getIdCat();
         Porduct.getBrand();
        ResultSet rs = stmt.executeQuery("INSERT INTO `product`(`idProduct`, `brand`, `warding`, `price`, `idCat`) " +
                "VALUES ("+Porduct.getIdProduct()+",'"+Porduct.getBrand()+"','"+Porduct.getWarding()+"',"+Porduct.getPrice()+","+Porduct.getIdCat()+") ");
    }
}