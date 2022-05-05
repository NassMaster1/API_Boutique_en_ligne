package ConnectDB;


import java.sql.*;


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



    //todo suprime ce main bdd
	  public static void main(String[] args) throws SQLException {
          getStatement();

      }




}
