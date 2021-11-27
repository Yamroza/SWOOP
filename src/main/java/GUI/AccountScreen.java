package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AccountScreen {

    @FXML
    private void ExitClicked(ActionEvent e) throws IOException {
        App.setRoot("mainScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void SaveChangesClicked(ActionEvent e) throws IOException {
        App.setRoot("offerScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void ChangePassClicked(ActionEvent e) throws IOException {
        App.setRoot("reg-chngpassScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }
}
