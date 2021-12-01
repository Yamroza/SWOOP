package GUI;

import Classes.User;
import Database.Connecting;
import Database.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class AccountScreen {

    public Button saveChangesButton;
    @FXML
    TextField loginName;

    @FXML
    TextField nameInput;

    @FXML
    TextField surnameInput;

    @FXML
    DatePicker dateInput;



    @FXML
    private void ExitClicked(ActionEvent e) throws IOException {
        App.setRoot("mainScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void SaveChangesClicked(ActionEvent e) throws IOException, SQLException {
        User loggedUser = Users.getLoggedUser();
        loggedUser.setName(nameInput.getText());
        loggedUser.setSurname(surnameInput.getText());
        loggedUser.updateDatabase();
    }

    @FXML
    private void ChangePassClicked(ActionEvent e) throws IOException {
        App.setRoot("reg-chngpassScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void initialize() throws SQLException {
        User loggedUser = Users.getLoggedUser();
        loginName.setText(loggedUser.getLogin());
        nameInput.setText(loggedUser.getName());
        surnameInput.setText(loggedUser.getSurname());
    }
}
