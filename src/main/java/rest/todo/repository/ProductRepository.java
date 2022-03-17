package rest.todo.repository;

import rest.todo.model.ProductModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ConnectDB.ConnectionDB.getStatement;


public class ProductRepository {

    private  ProductModel product=null;
    private final List<ProductModel> listProduct;
    private final Statement stmt = getStatement();

    public ProductRepository() throws SQLException {
        this.listProduct = new ArrayList<>();
    }

    public List<ProductModel> getListProduct() {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM product");
            // Extract data from result set
            while (rs.next()) {
                listProduct.add(new ProductModel(rs.getInt("idProduct"),
                        rs.getString("brand"),
                        rs.getString("warding"),
                        rs.getFloat("price"),
                        rs.getInt("idCat")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProduct;
    }


    public void postProduct(ProductModel product) throws SQLException {
        stmt.executeUpdate("INSERT INTO ws_rest.product ( brand, warding, price, idCat) VALUES ('"
                + product.getBrand() + "','"
                + product.getWarding() + "',"
                + product.getPrice() + ","
                + product.getIdCat() + ") ");
    }

    public int putProduct(int idproduct, ProductModel product) throws SQLException {
      return  stmt.executeUpdate("UPDATE ws_rest.product SET brand='"+
              product.getBrand() + "',warding='"+
              product.getWarding() + "',price= "+
              product.getPrice() + ",idCat="+
              product.getIdCat() + " WHERE idProduct=" +
              idproduct);
    }

    public int DeleteProduct(int idproduct) throws SQLException {
        return stmt.executeUpdate("DELETE  FROM ws_rest.product WHERE idProduct=" + idproduct);
    }

    public ProductModel GetProduct(int idProduct) throws SQLException {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM product Where idProduct="+idProduct);
            // Extract data from result set
            while (rs.next()) {
                 product= new ProductModel(rs.getInt("idProduct"),
                        rs.getString("brand"),
                        rs.getString("warding"),
                        rs.getFloat("price"),
                        rs.getInt("idCat")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}