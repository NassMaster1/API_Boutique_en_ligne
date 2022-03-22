package rest.todo.repository;
import rest.todo.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ConnectDB.ConnectionDB.getStatement;

public class UserRepository {

    private UserModel user=null;
    private final List<UserModel> listUser;
    private final Statement stmt = getStatement();


    public UserRepository() throws SQLException {
        this.listUser = new ArrayList<>();
    }

    public List<UserModel> getListUser() {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM usr");
            // Extract data from result set
            while (rs.next()) {
                listUser.add(new UserModel(rs.getInt("idUsr"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUser;
    }


    public void postUser(UserModel user) throws SQLException {
        stmt.executeUpdate("INSERT INTO ws_rest.usr (firstName,lastName,email,password,role) VALUES ('"
                + user.getFirstName() + "','"
                + user.getLastName() + "','"
                + user.getEmail()+ "','"
                + user.getPassword()+ "','"
                + user.getRole() + "') ");
    }

    public UserModel GetUser(int idUser) throws SQLException {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM usr Where idUsr="+idUser);
            // Extract data from result set
            while (rs.next()) {
                user= new UserModel(rs.getInt("idUsr"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public int putUser(int idUser, UserModel user) throws SQLException {
        return  stmt.executeUpdate("UPDATE ws_rest.usr SET firstName='"+
                user.getFirstName() +"',lastName='"+
                user.getLastName() +"',email= '"+
                user.getEmail() +"',password='"+
                user.getPassword()+"',role='" +
                user.getRole()+"'WHERE idUsr="+idUser );
    }

    public int DeleteUser(int idUser) throws SQLException {
        return stmt.executeUpdate("DELETE  FROM ws_rest.usr WHERE idUsr=" + idUser);
    }

    public boolean verifyAutentification(String email, String password) throws SQLException {
      ResultSet result=   stmt.executeQuery("SELECT * FROM usr Where email='"+email+"'AND password='"+password+"'");
      if(result.next())
          return true;
      else
          return false;
    }
}