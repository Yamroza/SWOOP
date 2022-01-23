package GUI;

import Database.Users;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    public void loadMain() throws IOException
    {
        App.setRoot("mainScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
        App.myStage.setX(320);
        App.myStage.setY(50);
    }

    @FXML
    private void LoginClicked() throws IOException, SQLException {
        String login = loginInput.getText();
        String password = passwordInput.getText();
        if (Users.loginCheck(login, password))
        {
            loadMain();
        }
        else {
            errorMessage.setVisible(true);
            errorMessage.setText("Login lub hasło jest niepoprawne");
        }
    }

    @FXML
    private void RegisterClicked() throws IOException {
        App.setRoot("registerScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void EnterClicked(KeyEvent e) throws IOException, SQLException{
        if(e.getCode() == KeyCode.ENTER) {
            LoginClicked();
        }
    }
}

