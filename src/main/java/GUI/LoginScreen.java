package GUI;

import Classes.*;

import Database.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class LoginScreen {


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
    private void LoginClicked(ActionEvent e) throws IOException {
        App.setRoot("mainScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
        App.myStage.setX(320);
        App.myStage.setY(50);
    }

    @FXML
    private void RegisterClicked(ActionEvent e) throws IOException {
        App.setRoot("mainScreen");
    }

    @FXML
    private void CancelClicked(ActionEvent e) throws IOException {
        App.setRoot("loginScreen");
    }

}

