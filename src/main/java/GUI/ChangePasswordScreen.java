package GUI;

import Classes.User;
import Database.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ChangePasswordScreen {

    @FXML
    PasswordField passwordInput;

    @FXML
    PasswordField repeatPasswordInput;

    @FXML
    Text errorMessage;

    @FXML
    private void CancelClicked(ActionEvent e) throws IOException {
        App.setRoot("accountScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void ChangeClicked(ActionEvent e) throws IOException, SQLException {
        String password = passwordInput.getText();
        String repeatedPassword = repeatPasswordInput.getText();
        if (!Objects.equals(password, repeatedPassword) )
        {
            errorMessage.setText("Passwords don't match.");
            return;
        }
        User loggedUser = Users.getLoggedUser();
        loggedUser.setPassword(password);
        loggedUser.updateDatabase();
        App.setRoot("accountScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }
}
