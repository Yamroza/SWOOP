package Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Categories {

    private static ObservableList<String> categoriesList = null;

    // returns list of categories
    public static ObservableList<String> getCategoriesList() throws SQLException {
        if (categoriesList == null){
            categoriesList = getCategories();
        }
        return categoriesList;
    }

    // downloading list of categories from database
    private static ObservableList<String> getCategories() throws SQLException {
        Connecting DB = new Connecting();
        Connection conn = DB.getConn();
        ObservableList<String> categories = FXCollections.observableArrayList();
        if (conn != null) {
            Statement stmt;
            stmt = conn.createStatement();
            try {
                ResultSet rs = stmt.executeQuery("Select NAME from CATEGORIES");
                while (rs.next()) {
                    categories.add(rs.getString("name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        DB.close();
        return categories;
    }
}
