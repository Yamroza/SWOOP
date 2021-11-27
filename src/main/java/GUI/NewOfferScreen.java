package GUI;

import Classes.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.Objects;

public class NewOfferScreen {

    @FXML
    private void CancelClicked(ActionEvent e) throws IOException {
        App.setRoot("mainScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void SaveClicked(ActionEvent e) throws IOException {
        App.setRoot("mainScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }
}
