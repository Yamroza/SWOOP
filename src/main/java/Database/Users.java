package Database;

import Classes.Category;
import Classes.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Users {

    static ArrayList<User> usersList = new ArrayList<>();
    static User loggedUser;


    public static boolean isUserInDatabase(String username) throws SQLException {
        Connecting DB = new Connecting();
        boolean success = false;
        Connection conn = DB.getConn();
        if (conn != null) {
            Statement stmt;
            stmt = conn.createStatement();
            try {
                ResultSet rs = stmt.executeQuery("SELECT * FROM Users WHERE Login LIKE '" + username + "'");
                while (rs.next()) {
                    success = true;
                    User user = new User();
                    user.setLogin(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("name"));
                    user.setSurname(rs.getString("surname"));
                    //user.setBirthDate(LocalDate.parse(rs.getString("birthdate")));
                    //user.setAccountCreationTime(LocalDate.parse(rs.getString("accountcreationdate")));
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
        return success;
    }


    public static boolean loginCheck (String username, String password) throws SQLException {
        boolean success = false;
        Connecting DB = new Connecting();
        Connection conn = DB.getConn();
        if (conn != null) {
            Statement stmt;
            stmt = conn.createStatement();
            try {
                ResultSet rs = stmt.executeQuery("SELECT * FROM Users WHERE Login LIKE '" + username + "'"
                + "AND Password LIKE '" + password + "'");
                while (rs.next()) {
                    success = true;
                    User user = new User();
                    user.setLogin(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("name"));
                    user.setSurname(rs.getString("surname"));
                    //user.setBirthDate(LocalDate.parse(rs.getString("birthdate")));
                    //user.setAccountCreationTime(LocalDate.parse(rs.getString("accountcreationdate")));
                    setLoggedUser(user);
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
        return success;
    }



    public static ArrayList<User> getUsersList() {
        return usersList;
    }

    public static User checkIfUserIsInBase(String givenUsername) {
        for (User user : usersList) {
            if (Objects.equals(user.getLogin(), givenUsername)) {
                return user;
            }
        }
        return null;
    }

    public static void addUser(String login, String password) {
        usersList.add(new User(login, password));
    }

    public static void setLoggedUser(User user) {
        loggedUser = user;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }
}
