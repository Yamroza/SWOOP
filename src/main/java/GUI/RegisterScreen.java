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

public class RegisterScreen {

    @FXML
    TextField loginInput;

    @FXML
    PasswordField passwordInput;

    @FXML
    PasswordField repeatPasswordInput;

    @FXML
    Text errorMessage;

    @FXML
    private void CancelClicked(ActionEvent e) throws IOException {
        App.setRoot("loginScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void RegisterClicked(ActionEvent e) throws IOException, SQLException {
        String password = passwordInput.getText();
        String repeatedPassword = repeatPasswordInput.getText();
        String login = loginInput.getText();
        if (!Objects.equals(password, repeatedPassword) )
        {
            errorMessage.setText("Passwords don't match.");
            return;
        }
        if (Users.isUserInDatabase(login))
        {
            errorMessage.setText("Login already exists.");
            return;
        }
        User newUser = new User(login, password);
        newUser.addToDatabase();
        App.setRoot("loginScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }
}
