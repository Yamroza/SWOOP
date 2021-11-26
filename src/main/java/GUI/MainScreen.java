package GUI;

import Classes.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.Objects;

public class MainScreen {

    @FXML
    private void ButtonClicked(ActionEvent e) throws IOException{
        App.setRoot("loginScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

}
