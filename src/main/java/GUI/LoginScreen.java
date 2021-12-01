package GUI;

import Classes.*;

import Database.Connecting;
import Database.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginScreen {
    public Button loginButton;
    public Button registerButton;


//
//    @FXML
//    private void LoginClick(ActionEvent event) throws IOException {
//        User user = Users.checkIfUserIsInBase(login.getText());
//        if (user == null) {
//            password.setText("");
//            incorrectCredentials.setText("No user with this username found.");
//        }
//        else {
//            if (Objects.equals(user.getPassword(), password.getText())) {
//                Users.setLoggedUser(user);
//                App.setRoot("loginTest");
//            }
//            else {
//                password.setText("");
//                incorrectCredentials.setText("Incorrect Password.");
//            }
//        }
//    }
//
//    @FXML
//    private void RegisterClick() throws IOException {
//        App.setRoot("registerTest");
//    }

    @FXML
    TextField loginInput;

    @FXML
    PasswordField passwordInput;

    @FXML
    private void LoginClicked(ActionEvent e) throws IOException, SQLException {
        Connecting DB = new Connecting();
        String login = loginInput.getText();
        String password = passwordInput.getText();
        if (Users.loginCheck(DB ,login, password))
        {
            App.setRoot("mainScreen");
            App.myStage.setScene(App.scene);
            App.myStage.sizeToScene();
            App.myStage.setX(320);
            App.myStage.setY(50);
        }
        DB.close();
    }

    @FXML
    private void RegisterClicked(ActionEvent e) throws IOException {
        App.setRoot("reg-chngpassScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void CancelClicked(ActionEvent e) throws IOException {
        App.setRoot("loginScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

}

