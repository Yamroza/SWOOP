package GUI;

import Classes.Comment;
import Classes.Offer;
import Classes.Transaction;
import Database.Comments;
import Database.Offers;
import Database.Transactions;
import Database.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    @FXML
    RadioButton exchangeButton;

    @FXML
    RadioButton buyButton;

    @FXML
    TextField exchangeOffer;

    ObservableList<Comment> comments;

    @FXML
    private void ExitClicked(ActionEvent e) throws IOException {
        Offers.setSelectedOffer(null);
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
    private void submitTransactionClicked() throws SQLException {
        if(exchangeButton.isSelected())
        {
            int offerID = Offers.getSelectedOffer().getOfferId();
            String buyer = Users.getLoggedUser().getLogin();
            String buyersOffer = exchangeOffer.getText();
            Transaction transaction = new Transaction(offerID, buyer, buyersOffer);
            Transactions.addTransactionToDatabase(transaction);
        }
        else if(buyButton.isSelected())
        {
            System.out.println("kupno");
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
        ObservableList<String> offerType = FXCollections.observableArrayList();
        if(currentOffer.getIsForSale())
        {
            buyButton.setDisable(false);
            buyButton.setSelected(true);
        }
        if(currentOffer.getIsForExchange())
        {
            exchangeButton.setDisable(false);
            exchangeOffer.setEditable(true);
            exchangeButton.setSelected(true);
        }
    }
}
