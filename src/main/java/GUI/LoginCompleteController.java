package GUI;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class LoginCompleteController {

    @FXML
    private Text loginMsg;

    @FXML
    private void initialize(){
        loginMsg.setText(App.getTestString());
    }

}
