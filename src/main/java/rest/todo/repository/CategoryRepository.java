package rest.todo.repository;

import rest.todo.model.CategoryModel;
import rest.todo.model.ProductModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ConnectDB.ConnectionDB.getStatement;

public class CategoryRepository {

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

    public void putProduct(CategoryModel category) throws SQLException {
        stmt.executeUpdate("INSERT INTO ws_rest.categories (nameC) VALUES ('"
                +category.getNameC()+"') ");
    }
}
