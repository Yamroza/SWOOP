package GUI;

import Classes.Comment;
import Classes.Offer;
import Classes.Transaction;
import Classes.User;
import Database.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
    Text description;

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

    @FXML
    ImageView offerPhoto;

    @FXML
    Button editButton;

    @FXML
    Button acceptChangesButton;

    @FXML
    TextField editNameField;

    @FXML
    Text editNameText;

    @FXML
    TextArea editDescriptionArea;

    @FXML
    ChoiceBox<String> editCategory;

    @FXML
    Button submitTransactionButton;

    @FXML
    TextField editPrice;

    @FXML
    Text editInfoText;

    @FXML
    Button editPhotoButton;

    ObservableList<Comment> comments;

    @FXML
    private void ExitClicked() throws IOException {
        if(!Objects.equals(Users.getLoggedUser().getLogin(), Offers.getSelectedOffer().getSeller())) {
            Offers.setSelectedOffer(null);
            App.setRoot("mainScreen");
            App.myStage.setScene(App.scene);
            App.myStage.sizeToScene();
        }
        else
        {
            Offers.setSelectedOffer(null);
            App.setRoot("accountScreen");
            App.myStage.setScene(App.scene);
            App.myStage.sizeToScene();
        }
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
            comments = Comments.getComments(Offers.getSelectedOffer().getOfferId());
            commentList.setItems(comments);
        }
    }

    @FXML
    private void submitTransactionClicked() throws SQLException, IOException {
        Offer selectedOffer = Offers.getSelectedOffer();
        int offerID = selectedOffer.getOfferId();
        String buyer = Users.getLoggedUser().getLogin();
        if(exchangeButton.isSelected())
        {
            String buyersOffer = exchangeOffer.getText();
            Transaction transaction = new Transaction(offerID, buyer, buyersOffer, 0);
            Transactions.addTransactionToDatabase(transaction);
            this.ExitClicked();
        }
        else if(buyButton.isSelected())
        {
            String buyersOffer = selectedOffer.getPrice() + " PLN";
            Transaction transaction = new Transaction(offerID, buyer, buyersOffer, 1);
            Transactions.addTransactionToDatabase(transaction);
            Offers.setOfferStatus(Offers.getSelectedOffer().getOfferId(), 1);
            this.ExitClicked();
        }
    }

    @FXML
    private void editClicked() throws SQLException {
        itemName.setDisable(true);
        itemName.setVisible(false);
        editButton.setDisable(true);
        editButton.setVisible(false);
        acceptChangesButton.setVisible(true);
        acceptChangesButton.setDisable(false);
        editNameText.setVisible(true);
        editNameText.setDisable(false);
        editNameField.setVisible(true);
        editNameField.setDisable(false);
        editDescriptionArea.setVisible(true);
        editDescriptionArea.setDisable(false);
        editCategory.setItems(Categories.getCategoriesList());
        editCategory.setDisable(false);
        editCategory.setVisible(true);
        category.setDisable(true);
        category.setVisible(false);
        editPhotoButton.setVisible(true);
        editPhotoButton.setDisable(false);
        if(Offers.getSelectedOffer().getIsForSale())
        {
            editPrice.setVisible(true);
            editPrice.setDisable(false);
            editPrice.setText(String.valueOf(Offers.getSelectedOffer().getPrice()));
        }
        editCategory.setValue(category.getText());
        editNameField.setText(itemName.getText());
        editDescriptionArea.setText(description.getText());
    }

    @FXML
    private void acceptChangesClicked() throws SQLException {
        Offer currentOffer = Offers.getSelectedOffer();
        try {
            currentOffer.setPrice(Float.valueOf(editPrice.getText()));
        }
        catch(NumberFormatException e){
            editInfoText.setVisible(true);
            editInfoText.setText("Zła wartość ceny");
            editInfoText.setFill(Color.RED);
            return;
        }
        if(Objects.equals(editNameField.getText(), ""))
        {
            editInfoText.setVisible(true);
            editInfoText.setText("Nazwa nie może być pusta");
            editInfoText.setFill(Color.RED);
            return;
        }
        currentOffer.setItemName(editNameField.getText());
        currentOffer.setItemDescription(editDescriptionArea.getText());
        currentOffer.setItemCategory(editCategory.getSelectionModel().getSelectedItem());
        itemName.setDisable(false);
        itemName.setVisible(true);
        editButton.setDisable(false);
        editButton.setVisible(true);
        acceptChangesButton.setVisible(false);
        acceptChangesButton.setDisable(true);
        editNameText.setVisible(false);
        editNameText.setDisable(true);
        editNameField.setVisible(false);
        editNameField.setDisable(true);
        editDescriptionArea.setVisible(false);
        editDescriptionArea.setDisable(true);
        editCategory.setDisable(true);
        editCategory.setVisible(false);
        category.setDisable(false);
        category.setVisible(true);
        editPrice.setVisible(false);
        editPrice.setDisable(true);
        editPhotoButton.setVisible(false);
        editPhotoButton.setDisable(true);
        Offers.updateOffer(currentOffer);
        itemName.setText(currentOffer.getItemName());
        description.setText(currentOffer.getItemDescription());
        category.setText(currentOffer.getItemCategory());
        itemPrice.setText(String.valueOf(currentOffer.getPrice()));
        editInfoText.setVisible(true);
        editInfoText.setText("Zapisano zmiany");
        editInfoText.setFill(Color.LIMEGREEN);

    }

    @FXML
    private void initialize() throws SQLException, IOException {
        Offer currentOffer = Offers.getSelectedOffer();
        Image image = Imgur.showImageFromLink(currentOffer.getPhoto());
        offerPhoto.setImage(image);

        itemName.setText(currentOffer.getItemName());
        itemPrice.setText(String.valueOf(currentOffer.getPrice()));
        isForExchange.setText(currentOffer.getIsForExchange() ? "Tak" : "Nie");
        category.setText(currentOffer.getItemCategory());
        seller.setText(currentOffer.getSeller());
        description.setText(currentOffer.getItemDescription());
        comments = Comments.getComments(Offers.getSelectedOffer().getOfferId());
        commentList.setItems(comments);
        commentList.setCellFactory(commentListView -> new CommentListElement());
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
        if(Objects.equals(currentOffer.getSeller(), Users.getLoggedUser().getLogin()))
        {
            editButton.setVisible(true);
            editButton.setDisable(false);
            submitTransactionButton.setDisable(true);
        }
    }

    final FileChooser fc = new FileChooser();
    String photoLink;

    public void changePhoto(ActionEvent actionEvent) throws Exception {
        fc.setTitle("Wybierz nowe zdjęcie oferty");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files","*.jpg","*.png", "*.bmp" ));
        File chosen_file = fc.showOpenDialog(null);
        if (chosen_file != null) {
            String link = Imgur.putImgurContent(chosen_file);
            this.photoLink = link.replaceAll("\\\\", "/");
            Users.getLoggedUser().setProfilePhoto(this.photoLink);
            Image image = Imgur.showImageFromLink(this.photoLink);
            offerPhoto.setImage(image);
            String insert = "UPDATE offers SET photo = '" + this.photoLink + "' WHERE name LIKE('" + Offers.getSelectedOffer().getItemName() + "')";
            Connecting DB = new Connecting();
            DB.alterTable(insert);
            DB.close();
        }
    }
}
