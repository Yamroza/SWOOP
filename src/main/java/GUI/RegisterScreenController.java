package GUI;

import Database.Users;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class RegisterScreenController {

    @FXML
    private Text errorMsg;

    @FXML
    private TextField newLogin;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField passwordConfirm;


    @FXML
    private void buttonClick() throws IOException {
        String loginText = newLogin.getText();
        String passwordText = newPassword.getText();
        if (Objects.equals(loginText, "")) {
            errorMsg.setText("Login can't be empty.");
        }
        else if (Users.checkIfUserIsInBase(loginText) != null) {
            errorMsg.setText("User with that login exist.");
        }
        else if (Objects.equals(passwordText, "")) {
            errorMsg.setText("Password can't be empty.");
        }
        else if (!Objects.equals(passwordText, passwordConfirm.getText())) {
            errorMsg.setText("Passwords aren't the same.");
        }
        else {
            Users.addUser(loginText, passwordText);
            App.setRoot("loginScreen");
        }
    }
}
