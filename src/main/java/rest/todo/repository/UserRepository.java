package rest.todo.repository;

import rest.todo.model.ProductModel;
import rest.todo.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ConnectDB.ConnectionDB.getStatement;

public class UserRepository {

    private ProductModel product=null;
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

    public ProductModel GetUser(int idUser) throws SQLException {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM product Where idProduct="+idUser);
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
