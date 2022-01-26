package Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void testGetCategoriesList() throws SQLException {
        ObservableList<String> list = Categories.getCategoriesList();

        Assert.assertEquals(10, list.size());
    }
}