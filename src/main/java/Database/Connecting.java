package Database;
import Classes.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Connecting {

    private static final Logger log = LoggerFactory.getLogger(Connecting.class);
    private Connection conn;
    private static final String url = "jdbc:oracle:thin:@//ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
    private static String login = "z27";
    private static String password = "9wdzsz";
    private String sql_instr = null;

    public Connecting() throws SQLException {
        log.info("Opening connection to najlepszyzespol database");
        conn = DriverManager.getConnection(url, login, password);
    }

    public Connecting(String login, String password) throws SQLException {
        Connecting.login = login;
        Connecting.password = password;
        log.info("Opening connection to najlepszyzespol database");
        conn = DriverManager.getConnection(url, Connecting.login, Connecting.password);
    }

    public Connection getConn() {
        return conn;
    }

    public void close() {
        if (conn != null) {
            try {
                log.info("Closing database connection to najlepszyzespol database");
                conn.close();
            } catch (SQLException ex) {
                log.error("Unable to close connection", ex);
            }
            conn = null;
        }
    }

    public void alterTable(String sql_in) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute(sql_in);
            log.info("Executing request");
        } catch (SQLException e) {
            log.error("Unable to execute request", e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    public List<Category> getCategories() {
        List<Category>categories = new ArrayList<Category>();
        if (conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM CATEGORIES");
                while (rs.next()) {
                    Category category = new Category();
                    category.setCategory_id(rs.getInt("category_id"));
                    category.setName(rs.getString("name"));
                    categories.add(category);
                }
            } catch (SQLException e) {
                log.error("Unable to get categories", e);
            } finally {
                if (stmt != null) {
                    try { stmt.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }
        return categories;
    }

    public List<User> getUsers() {
        List<User>users = new ArrayList<User>();
        if (conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");
                while (rs.next()) {
                    User user = new User();
                    user.setLogin(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("name"));
                    user.setSurname(rs.getString("surname"));
                    //user.setBirthDate(LocalDate.parse(rs.getString("birthdate")));
                    //user.setAccountCreationDate(LocalDate.parse(rs.getString("accountcreationdate")));
                    users.add(user);
                }
            } catch (SQLException e) {
                log.error("Unable to get categories", e);
            } finally {
                if (stmt != null) {
                    try { stmt.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }
        return users;
    }
}

