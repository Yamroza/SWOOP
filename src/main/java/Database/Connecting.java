package Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;


public class
Connecting {

    private static final Logger log = LoggerFactory.getLogger(Connecting.class);
    private Connection conn;
    private static final String url = "jdbc:oracle:thin:@//ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
    private static String login = "z27";
    private static String password = "9wdzsz";

    public Connection getConn() {
        return conn;
    }

    // crating connection to database
    public Connecting() throws SQLException {
        try {
            log.info("Opening connection to najlepszyzespol database");
            conn = DriverManager.getConnection(url, login, password);
        }
        catch (SQLException ex) {
            log.error("Unable to open connection", ex);
        }
    }

    // closing connection to database
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

    // executing specified query on database
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
}

