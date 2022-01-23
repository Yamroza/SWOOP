package Database;

import Classes.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Users {

    static User loggedUser;

    // converting string to date format
    private static LocalDate parseStringToDate(String date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime resultDate = LocalDateTime.parse(date, formatter);
        return resultDate.toLocalDate();
    }

    // converting date format to string
    public static String parseDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    // creating a user object from result-set from database
    private static User returnUser(ResultSet rs) throws SQLException{
        User user = new User();
        user.setLogin(rs.getString("login"));
        user.setPasswordNoHash(rs.getString("password"));
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
        user.setProfilePhoto(rs.getString("profile_photo"));
        return user;
    }


    // checking if username is already taken by another user
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


    // checking if given username and password make a correct pair in database
    public static boolean loginCheck (String username, String password) throws SQLException {
        password = DigestUtils.shaHex(password);
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

    // generating sql user insert command for database
    private static String generateInsert(User user){
        return ("INSERT INTO USERS (login, password, name, surname, birthdate, accountcreationdate, profile_photo) VALUES " +
                "('" + user.getLogin() + "' , '" + user.getPassword()  + "' , '" + user.getName() + "' , '" +
                user.getSurname() + "' , TO_DATE('" + parseDateToString(user.getBirthDate()) + "', 'YYYY-MM-DD'), " +
                "TO_DATE('" + parseDateToString(user.getAccountCreationDate()) + "', 'YYYY-MM-DD'), '" + user.getProfilePhoto() + "')");
    }

    // generating sql user update command for database
    private static String generateUpdate(){
        return ("UPDATE users SET password = '" + loggedUser.getPassword() +"', name = '" + loggedUser.getName() +
                "', surname = '" + loggedUser.getSurname() +"' " +
                ", birthdate = TO_DATE('" + parseDateToString(loggedUser.getBirthDate()) + "', 'YYYY-MM-DD') " +
                "WHERE login = '" + loggedUser.getLogin() + "'");
    }

    // adding user to database
    public static void addUserToDatabase(User user) throws SQLException {
        Connecting DB = new Connecting();
        DB.alterTable(generateInsert(user));
        DB.close();
    }

    // updating user data in database
    public static void updateUserInDatabase() throws SQLException
    {
        Connecting DB = new Connecting();
        DB.alterTable(generateUpdate());
        DB.close();
    }

    // setting logged user variable
    public static void setLoggedUser(User user) {
        loggedUser = user;
    }

    // checking which user is currently logged in
    public static User getLoggedUser() {
        return loggedUser;
    }
}
