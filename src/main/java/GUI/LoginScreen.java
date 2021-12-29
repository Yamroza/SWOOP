package GUI;

import Database.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class LoginScreen {

    @FXML
    TextField loginInput;

    @FXML
    PasswordField passwordInput;

    @FXML
    Text errorMessage;

    @FXML
    private void LoginClicked() throws IOException, SQLException {
        String login = loginInput.getText();
        String password = passwordInput.getText();
        if (Users.loginCheck(login, password))
        {
            App.setRoot("mainScreen");
            App.myStage.setScene(App.scene);
            App.myStage.sizeToScene();
            App.myStage.setX(320);
            App.myStage.setY(50);
        }
        errorMessage.setText("Login or password is incorrect.");
    }

    @FXML
    private void RegisterClicked() throws IOException {
        App.setRoot("registerScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }
}

