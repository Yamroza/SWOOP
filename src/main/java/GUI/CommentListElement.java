package GUI;

import Classes.Comment;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class CommentListElement extends ListCell<Comment> {

    @FXML
    Text commenterName;

    @FXML
    Text commentText;

    @FXML
    AnchorPane commentCell;

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
            commenterName.setText(comment.getCommenterName());
            commentText.setText(comment.getCommentText());
        }

        setText(null);
        setGraphic(commentCell);
    }

}
