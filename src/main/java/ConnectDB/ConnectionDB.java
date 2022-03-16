package ConnectDB;

import rest.todo.dao.DaoProduct;
import rest.todo.model.ProductModel;

import java.sql.*;
import java.util.List;

import static org.apache.catalina.util.URLEncoder.QUERY;

public class ConnectionDB {

    static final String DB_URL = "jdbc:mysql://localhost/ws-rest";
    static final String USER = "root";
    static final String PASS = "";


    public static Connection getDBConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return(conn);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Statement getStatement() throws SQLException {
        Connection conx=getDBConnection();
        return conx.createStatement();
    }



    //todo suprime ce main bdd
	  public static void main(String[] args) throws SQLException {
          DaoProduct daoProduct = new DaoProduct();
          List a=daoProduct.getListProduct();

          for (Object elem: a) {
              System.out.println(elem.toString());
          }

          ProductModel Porduct=new ProductModel(1,"dell","assa",1200,23);
          String s=   "INSERT INTO `product`(`idProduct`, `brand`, `warding`, `price`, `idCat`) " +
                  "VALUES ("+Porduct.getIdProduct()+",'"+Porduct.getBrand()+"','"+Porduct.getWarding()+"',"+Porduct.getPrice()+","+Porduct.getIdCat()+") ";
          System.out.println(s);
	   }


}
