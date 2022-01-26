package GUI;

import Database.Users;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import org.controlsfx.control.MaskerPane;

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
    MaskerPane waiting;


    public void loadMain() throws IOException
    {
        App.setRoot("mainScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
        App.myStage.setX(320);
        App.myStage.setY(50);
    }

    @FXML
    private void LoginClicked() {
        waiting.setVisible(true);
        waiting.toFront();
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws SQLException {
                String login = loginInput.getText();
                String password = passwordInput.getText();
                if (Users.loginCheck(login, password)) {
                    this.cancel();
                } else {
                    errorMessage.setVisible(true);
                    errorMessage.setText("Login lub hasło jest niepoprawne");
                }

                return null;
            }
        };
        task.setOnSucceeded((workerStateEvent) -> {
            waiting.setVisible(false);
            waiting.toBack();
        });
        task.setOnCancelled((workerStateEvent -> {
            try {
                loadMain();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        new Thread(task).start();
    }

    @FXML
    private void RegisterClicked() throws IOException {
        App.setRoot("registerScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void EnterClicked(KeyEvent e) {
        if(e.getCode() == KeyCode.ENTER) {
            LoginClicked();
        }
    }
}

