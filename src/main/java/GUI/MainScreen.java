package GUI;

import Classes.*;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import java.io.IOException;
import java.util.Objects;

public class MainScreen {

    @FXML
    private void NewOfferClicked(ActionEvent e) throws IOException {
        App.setRoot("newOfferScreen");
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
