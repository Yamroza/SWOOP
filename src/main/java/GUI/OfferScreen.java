package GUI;

import Classes.Comment;
import Classes.Offer;
import Database.Comments;
import Database.Offers;
import Database.Users;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class OfferScreen {

    @FXML
    Text itemName;

    @FXML
    Text itemPrice;

    @FXML
    Text isForExchange;

    @FXML
    Text category;

    @FXML
    Text seller;

    @FXML
    TextArea description;

    @FXML
    ListView<Comment> commentList;

    @FXML
    TextField commentTextField;

    @FXML
    Button addCommentButton;

    ObservableList<Comment> comments;

    @FXML
    private void ExitClicked(ActionEvent e) throws IOException {
        App.setRoot("mainScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void addCommentClicked() throws SQLException {
        String commentText = commentTextField.getText();
        if(!Objects.equals(commentText, ""))
        {
            int offerID = Offers.getSelectedOffer().getOfferId();
            String commenterLogin = Users.getLoggedUser().getLogin();
            Comment comment = new Comment(offerID, commenterLogin, commentText);
            Comments.insertComment(comment);
            commentTextField.setText("");
            comments.add(0, comment);
            commentList.setItems(comments);
        }
    }

    @FXML
    private void initialize() throws SQLException {
        Offer currentOffer = Offers.getSelectedOffer();
        itemName.setText(currentOffer.getItemName());
        itemPrice.setText(String.valueOf(currentOffer.getPrice()));
        isForExchange.setText(currentOffer.getIsForExchange() ? "Tak" : "Nie");
        category.setText(currentOffer.getItemCategory());
        seller.setText(currentOffer.getSeller());
        description.setText(currentOffer.getItemDescription());
        comments = Comments.getComments(Offers.getSelectedOffer().getOfferId());
        commentList.setItems(comments);
        commentList.setCellFactory(commentListView -> new CommentListElement());
    }
}
