package GUI;

import Classes.Offer;
import Classes.Transaction;
import Classes.User;
import Database.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
    ListView<Transaction> transactionList;

    @FXML
    ListView<Transaction> userTransactionList;

    @FXML
    ImageView profilePicture;


    @FXML
    private void ExitClicked() throws IOException {
        App.setRoot("mainScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void logoutClicked() throws IOException {
        Users.setLoggedUser(null);
        App.setRoot("loginScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
        App.myStage.centerOnScreen();
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
    private void userOfferClicked() throws IOException {
        Offer selectedOffer = userOfferList.getSelectionModel().getSelectedItem();
        if (selectedOffer != null) {
            if(selectedOffer.getStatus() != 1) {
                Offers.setSelectedOffer(selectedOffer);
                App.setRoot("offerScreen");
                App.myStage.setScene(App.scene);
                App.myStage.sizeToScene();
            }
        }
    }

    @FXML
    private void initialize() throws SQLException, IOException {
        App.myStage.sizeToScene();
        App.myStage.centerOnScreen();
        User loggedUser = Users.getLoggedUser();

        URL url = new URL(loggedUser.getProfilePhoto());
        this.photoLink = loggedUser.getProfilePhoto();
        File file = new File("work_image.png");
        int connectionTimeout = 10 * 1000; // 10 sec
        int readTimeout = 300 * 1000; // 3 min
        FileUtils.copyURLToFile(url, file, connectionTimeout, readTimeout);
        Image image = new Image(file.toURI().toString());
        profilePicture.setImage(image);

        loginName.setText(loggedUser.getLogin());
        nameInput.setText(loggedUser.getName());
        surnameInput.setText(loggedUser.getSurname());
        dateInput.setValue(loggedUser.getBirthDate());
        ObservableList<Offer> offers = Offers.getNextTenUserOffers(loggedUser.getLogin());
        userOfferList.setItems(offers);
        userOfferList.setCellFactory(offerListView -> new UserOfferListElement());
        ObservableList<Transaction> transactions = Transactions.getTransactions(loggedUser.getLogin());
        transactionList.setItems(transactions);
        transactionList.setCellFactory(offerListView -> new TransactionListElement());
        ObservableList<Transaction> userTransactions = Transactions.getUserTransactions(loggedUser.getLogin());
        userTransactionList.setItems(userTransactions);
        userTransactionList.setCellFactory(offerListView -> new UserTransactionListElement());
    }

    final FileChooser fc = new FileChooser();
    String photoLink;

    public void ChangeProfileClicked() throws Exception {
        fc.setTitle("Wybierz nowe zdjęcie");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files","*.jpg","*.png", "*.bmp" ));
        File chosen_file = fc.showOpenDialog(null);
        if (chosen_file != null) {
            String link = Imgur.putImgurContent(chosen_file);
            this.photoLink = link.replaceAll("\\\\", "/");
            Users.getLoggedUser().setProfilePhoto(this.photoLink);
            Image image = Imgur.showImageFromLink(this.photoLink);
            profilePicture.setImage(image);
            //System.out.println("New image link: " + this.photoLink);
            String insert = "UPDATE users SET profile_photo = '" + this.photoLink + "' WHERE login LIKE('" + Users.getLoggedUser().getLogin() + "')";
            Connecting DB = new Connecting();
            DB.alterTable(insert);
            DB.close();
        }

    }
}
