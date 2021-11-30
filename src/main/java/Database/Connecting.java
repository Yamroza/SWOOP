package Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;


public class Connecting {

    // pola
    private static final Logger log = LoggerFactory.getLogger(Connecting.class);
    private Connection conn;
    private static String url = "jdbc:oracle:thin:@//ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
    private static String username = "z27";
    private static String password = "9wdzsz";

    // konstruktory
    public Connecting() throws SQLException {
        log.info("Opening connection to najlepszyzespol database");
        conn = DriverManager.getConnection(url, username, password);
    }

    public Connecting(String username, String password) throws SQLException {
        this.username = username;
        this.password = password;
//        log.info("Opening connection to najlepszyzespol database");
        conn = DriverManager.getConnection(url, this.username, this.password);
    }

    // getter i setter
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    // zamykanie bazy
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

    // zwraca cos co ja nie wiem czym jest
    public ResultSet query(String query) throws SQLException
    {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }
}

