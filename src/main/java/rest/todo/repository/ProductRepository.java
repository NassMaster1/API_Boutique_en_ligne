package rest.todo.repository;

import rest.todo.model.ProductModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ConnectDB.ConnectionDB.getStatement;


public class ProductRepository {

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

    public ProductModel getProduct(int id) {
        return listProduct.get(id);
    }

    public void postProduct(ProductModel product) throws SQLException {
        stmt.executeUpdate("INSERT INTO ws_rest.product ( brand, warding, price, idCat) VALUES ('"
                + product.getBrand() + "','"
                + product.getWarding() + "',"
                + product.getPrice() + ","
                + product.getIdCat() + ") ");
    }

    public void putProduct(ProductModel product) throws SQLException {
        stmt.execute("UPDATE ws_rest.product SET brand= '" +
                product.getBrand() + "', warding ='" +
                product.getWarding() + "', price = " +
                product.getPrice() + ", idCat =" +
                product.getIdCat() + " WHERE" +
                product.getIdProduct());


    }
}