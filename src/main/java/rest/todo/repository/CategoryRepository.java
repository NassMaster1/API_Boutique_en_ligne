package rest.todo.repository;

import rest.todo.model.CategoryModel;
import rest.todo.model.ProductModel;
import rest.todo.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ConnectDB.ConnectionDB.getStatement;

public class CategoryRepository {

    private CategoryModel category=null;
    private final List<CategoryModel> modelList;
    private final Statement stmt = getStatement();

    public CategoryRepository() throws SQLException {
        this.modelList =new ArrayList<>();
    }

    public List<CategoryModel> getModelList() {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM categories");
            // Extract data from result set
            while (rs.next()) {
                modelList.add(
                        new CategoryModel(rs.getInt("idCategory"),
                                rs.getString("nameC")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelList;
    }

    public void postCategory(CategoryModel category) throws SQLException {
        stmt.executeUpdate("INSERT INTO ws_rest.categories (nameC) VALUES ('"
                +category.getNameC()+"') ");
    }

    public CategoryModel GetCategory(int idCategory) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM categories Where idCategory="+idCategory);
            // Extract data from result set
            while (rs.next()) {
                category= new CategoryModel(rs.getInt("idCategory"),
                        rs.getString("nameC")
                        );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }


    public int putCategory(int idCategory, CategoryModel category) throws SQLException {
        return  stmt.executeUpdate("UPDATE ws_rest.categories SET namec='"+
                category.getNameC()+"'WHERE idCategory="+idCategory );
    }

    public int DeleteUser(int idCategory) throws SQLException {
        return stmt.executeUpdate("DELETE  FROM ws_rest.categories WHERE idCategory="+ idCategory);
    }


}
