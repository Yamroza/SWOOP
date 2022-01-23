package Database;

import Classes.Comment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Comments {

    // creating a comment object from result-set from database
    private static Comment returnComment(ResultSet rs) throws SQLException {
        int commentID = rs.getInt("comment_id");
        int offerID = rs.getInt("offer_id");
        String userLogin = rs.getString("user_login");
        String commentText = rs.getString("comment_text");
        return new Comment(commentID, offerID, userLogin, commentText);
    }

    // downloading all comments under specified offer
    public static ObservableList<Comment> getComments(int offerID) throws SQLException {
        Connecting DB = new Connecting();
        Connection conn = DB.getConn();
        ObservableList<Comment> comments = FXCollections.observableArrayList();
        if (conn != null) {
            Statement stmt;
            stmt = conn.createStatement();
            try {
                ResultSet rs = stmt.executeQuery("Select * from Comments " +
                        "Where offer_id like " + offerID + " Order By Comment_id Desc");
                while (rs.next()) {
                    comments.add(returnComment(rs));
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
        return comments;
    }

    // generating database command for inserting a comment
    private static String generateComment(Comment comment)
    {
        int offerID = comment.getOfferID();
        String userLogin = comment.getCommenterName();
        String commentText = comment.getCommentText();
        return "insert into COMMENTS (OFFER_ID, USER_LOGIN, COMMENT_TEXT) VALUES " +
                "(" + offerID + ", '" + userLogin + "', '" + commentText + "')";
    }

    // generating database command for altering a comment
    private static String generateCommentEdit(Comment comment, String newComment)
    {
        return ("UPDATE COMMENTS SET COMMENT_TEXT = '" + newComment +
                "' WHERE COMMENT_ID = " + comment.getCommentID());
    }

    // generating database command for deleting a comment
    private static String generateCommentDelete(Comment comment)
    {
        return ("DELETE FROM COMMENTS WHERE COMMENT_ID = " + comment.getCommentID());
    }

    // inserting comment to database
    public static void insertComment(Comment comment) throws SQLException {
        Connecting DB = new Connecting();
        DB.alterTable(generateComment(comment));
        DB.close();
    }

    // editing comment in database
    public static void editComment(Comment comment, String newComment) throws SQLException {
        Connecting DB = new Connecting();
        DB.alterTable(generateCommentEdit(comment, newComment));
        DB.close();
    }

    // deleting comment in database
    public static void deleteComment(Comment comment) throws SQLException {
        Connecting DB = new Connecting();
        DB.alterTable(generateCommentDelete(comment));
        DB.close();
    }
}
