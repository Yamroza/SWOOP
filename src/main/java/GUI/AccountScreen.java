package GUI;

import Classes.Offer;
import Classes.User;
import Database.Offers;
import Database.Users;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class AccountScreen {

    public Button saveChangesButton;

    @FXML
    Text loginName;

    @FXML
    TextField nameInput;

    @FXML
    TextField surnameInput;

    @FXML
    DatePicker dateInput;

    @FXML
    ListView<Offer> userOfferList;

    @FXML
    ListView<Offer> transactionList;



    @FXML
    private void ExitClicked() throws IOException {
        App.setRoot("mainScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void SaveChangesClicked() throws SQLException {
        User loggedUser = Users.getLoggedUser();
        loggedUser.setName(nameInput.getText());
        loggedUser.setSurname(surnameInput.getText());
        loggedUser.setBirthDate(dateInput.getValue());
        Users.updateUserInDatabase();
    }

    @FXML
    private void ChangePassClicked() throws IOException {
        App.setRoot("changePasswordScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void initialize() throws SQLException {
        User loggedUser = Users.getLoggedUser();
        loginName.setText(loggedUser.getLogin());
        nameInput.setText(loggedUser.getName());
        surnameInput.setText(loggedUser.getSurname());
        dateInput.setValue(loggedUser.getBirthDate());
        ObservableList<Offer> offers = Offers.getNextTenUserOffers(loggedUser.getLogin());
        userOfferList.setItems(offers);
        userOfferList.setCellFactory(offerListView -> new OfferListElement());
        transactionList.setItems(offers);
        transactionList.setCellFactory(offerListView -> new OfferListElement());
    }
}
