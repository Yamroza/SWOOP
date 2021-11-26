package GUI;

import Classes.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.Objects;

public class MainScreen {

    @FXML
    private void NewOfferClicked(ActionEvent e) throws IOException{
        App.setRoot("offerScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void MyAccountClicked(ActionEvent e) throws IOException{
        App.setRoot("accountScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

}
