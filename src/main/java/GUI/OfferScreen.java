package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class OfferScreen {

    @FXML
    private void ExitClicked(ActionEvent e) throws IOException {
        App.setRoot("mainScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }
}
