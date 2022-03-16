package ConnectDB;

import rest.todo.repository.ProductRepository;
import rest.todo.model.ProductModel;

import java.sql.*;
import java.util.List;

public class ConnectionDB {

    static final String DB_URL = "jdbc:mysql://localhost/ws_rest";
    static final String USER = "root";
    static final String PASS = "";


    public static Connection getDBConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return(conn);
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    public static Statement getStatement() throws SQLException {
        Connection conx=getDBConnection();
        return conx.createStatement();
    }

	  public static void main(String[] args) throws SQLException {
          ProductRepository daoProduct = new ProductRepository();
          List<ProductModel> a=daoProduct.getListProduct();

          for (Object elem: a) {
              System.out.println(elem.toString());
          }

          ProductModel Product=new ProductModel(1,"dell","assa",1200,23);
          String s=  "INSERT INTO product (idProduct, brand, warding, price, idCat) VALUES ("
                  +Product.getIdProduct()+",'"
                  +Product.getBrand()+"','"
                  +Product.getWarding()+"',"
                  +Product.getPrice()+","
                  +Product.getIdCat() +")";

                  System.out.println(s);
	   }


}
