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
        if (Objects.equals(login, ""))
        {
            errorMessage.setText("Login nie może być pusty");
            errorMessage.setVisible(true);
            return;
        }
        if (!Objects.equals(password, repeatedPassword)  || Objects.equals(password, ""))
        {
            errorMessage.setText("Hasła się nie zgadzają");
            errorMessage.setVisible(true);
            return;
        }
        if (Users.isUserInDatabase(login))
        {
            errorMessage.setText("Ten login jest już zajęty");
            errorMessage.setVisible(true);
            return;
        }
        User newUser = new User(login, password);
        Users.addUserToDatabase(newUser);
        App.setRoot("loginScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }
}
