package GUI;

import Classes.Comment;
import Classes.Offer;
import Database.Comments;
import Database.Connecting;
import Database.Imgur;
import Database.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class CommentListElement extends ListCell<Comment> {

    @FXML
    Text commenterName;

    @FXML
    Text commentText;

    @FXML
    AnchorPane commentCell;

    @FXML
    Button editCommentButton;

    @FXML
    Button deleteCommentButton;

    @FXML
    Button updateCommentButton;

    @FXML
    Button undoChangesButton;

    @FXML
    TextArea commentEditor;

    @FXML
    ImageView CommentProfPicture;


    private FXMLLoader loader;

    @Override
    protected void updateItem(Comment comment, boolean empty)
    {
        super.updateItem(comment, empty);
        if (empty || comment == null)
        {
            setText(null);
            setGraphic(null);
        }
        else
        {
            if(loader == null)
            {
                loader = new FXMLLoader(App.class.getResource("/commentListCell.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            editCommentButton.setOnAction(arg0 -> {
                commentText.setVisible(false);
                deleteCommentButton.setDisable(true);
                deleteCommentButton.setVisible(false);
                commentEditor.setVisible(true);
                commentEditor.setDisable(false);
                commentEditor.setText(commentText.getText());
                updateCommentButton.setVisible(true);
                updateCommentButton.setDisable(false);
                undoChangesButton.setVisible(true);
                undoChangesButton.setDisable(false);
            });
            undoChangesButton.setOnAction(arg0 -> {
                commentText.setVisible(true);
                deleteCommentButton.setDisable(false);
                deleteCommentButton.setVisible(true);
                commentEditor.setVisible(false);
                commentEditor.setDisable(true);
                updateCommentButton.setVisible(false);
                updateCommentButton.setDisable(true);
                undoChangesButton.setVisible(false);
                undoChangesButton.setDisable(true);
            });
            updateCommentButton.setOnAction(arg0 -> {
                try {
                    Comments.editComment(getItem(), commentEditor.getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    App.setRoot("offerScreen");
                    App.myStage.setScene(App.scene);
                    App.myStage.sizeToScene();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            deleteCommentButton.setOnAction(arg0 -> {
                try {
                    Comments.deleteComment(getItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    App.setRoot("offerScreen");
                    App.myStage.setScene(App.scene);
                    App.myStage.sizeToScene();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            if(Objects.equals(Users.getLoggedUser().getLogin(), getItem().getCommenterName()))
            {
                editCommentButton.setDisable(false);
                editCommentButton.setVisible(true);
                deleteCommentButton.setDisable(false);
                deleteCommentButton.setVisible(true);
            }
            commenterName.setText(comment.getCommenterName());
            commentText.setText(comment.getCommentText());
            String login = comment.getCommenterName();
            String select = "SELECT * FROM users WHERE login LIKE('" + login + "')";
            String photoUrl = "https://i.imgur.com/c9Bk75O.png";
            Connecting DB = null;
            try {
                DB = new Connecting();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Connection conn = DB.getConn();
            if (conn != null) {
                Statement stmt = null;
                try {
                    stmt = conn.createStatement();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    ResultSet rs = stmt.executeQuery(select);
                    while (rs.next()) {
                        photoUrl = rs.getString("profile_photo");
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
            Image image = null;
            try {
                image = Imgur.showImageFromLink(photoUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            CommentProfPicture.setImage(image);
        }

        setText(null);
        setGraphic(commentCell);
    }

}
