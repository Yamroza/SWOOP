package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.IOException;

public class LoginScreenController {

    @FXML
    private TextField login;

    @FXML
    private void LoginClick(ActionEvent event) throws IOException {
        App.setText(login.getText());
        App.setRoot("loginTest");
    }

    @FXML
    private void RegisterClick() throws IOException {
        App.setRoot("registerTest");
    }

}

