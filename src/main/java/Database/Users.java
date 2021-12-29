package Database;

import Classes.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class Users {

    static User loggedUser;

    private static LocalDate parseDate(String date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime resultDate = LocalDateTime.parse(date, formatter);
        return resultDate.toLocalDate();
    }

    private static User returnUser(ResultSet rs) throws SQLException{
        User user = new User();
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        String birthDate = rs.getString("birthdate");
        if(birthDate != null) {
            user.setBirthDate(parseDate(birthDate));
        }
        String accountCreationDate = rs.getString("accountcreationdate");
        if(accountCreationDate != null)
        {
            user.setAccountCreationDate(parseDate(accountCreationDate));
        }
        return user;
    }


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
                    setLoggedUser(returnUser(rs));
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


    public static void setLoggedUser(User user) {
        loggedUser = user;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }
}
