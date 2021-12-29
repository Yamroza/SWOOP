package Database;

import Classes.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Users {

    static User loggedUser;

    private static LocalDate parseStringToDate(String date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime resultDate = LocalDateTime.parse(date, formatter);
        return resultDate.toLocalDate();
    }

    public static String parseDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private static User returnUser(ResultSet rs) throws SQLException{
        User user = new User();
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        String birthDate = rs.getString("birthdate");
        if(birthDate != null) {
            user.setBirthDate(parseStringToDate(birthDate));
        }
        String accountCreationDate = rs.getString("accountcreationdate");
        if(accountCreationDate != null)
        {
            user.setAccountCreationDate(parseStringToDate(accountCreationDate));
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

    private static String generateInsert(User user){
        return ("INSERT INTO USERS (login, password, name, surname, birthdate, accountcreationdate) VALUES " +
                "('" + user.getLogin() + "' , '" + user.getPassword()  + "' , '" + user.getName() + "' , '" +
                user.getSurname() + "' , TO_DATE('" + parseDateToString(user.getBirthDate()) + "', 'YYYY-MM-DD'), " +
                "TO_DATE('" + parseDateToString(user.getAccountCreationDate()) + "', 'YYYY-MM-DD') )");
    }

    private static String generateUpdate(){
        return ("UPDATE users SET password = '" + loggedUser.getPassword() +"', name = '" + loggedUser.getName() +
                "', surname = '" + loggedUser.getSurname() +"' " +
                ", birthdate = TO_DATE('" + parseDateToString(loggedUser.getBirthDate()) + "', 'YYYY-MM-DD') " +
                "WHERE login = '" + loggedUser.getLogin() + "'");
    }

    public static void addUserToDatabase(User user) throws SQLException {
        Connecting DB = new Connecting();
        DB.alterTable(generateInsert(user));
        DB.close();
    }

    public static void updateUserInDatabase() throws SQLException
    {
        Connecting DB = new Connecting();
        DB.alterTable(generateUpdate());
        DB.close();
    }

    public static void setLoggedUser(User user) {
        loggedUser = user;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }
}
