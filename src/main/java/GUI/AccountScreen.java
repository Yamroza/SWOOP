package GUI;

import Classes.User;
import Database.Users;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
    private void initialize() {
        User loggedUser = Users.getLoggedUser();
        loginName.setText(loggedUser.getLogin());
        nameInput.setText(loggedUser.getName());
        surnameInput.setText(loggedUser.getSurname());
        dateInput.setValue(loggedUser.getBirthDate());
    }
}
