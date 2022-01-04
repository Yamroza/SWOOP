package GUI;

import Classes.Offer;
import Database.Offers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.io.IOException;

public class OfferScreen {

    @FXML
    Text itemName;

    @FXML
    Text itemPrice;

    @FXML
    Text isForExchange;

    @FXML
    Text category;

    @FXML
    Text seller;

    @FXML
    TextArea description;


    @FXML
    private void ExitClicked(ActionEvent e) throws IOException {
        App.setRoot("mainScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void initialize() {
        Offer currentOffer = Offers.getSelectedOffer();
        itemName.setText(currentOffer.getItemName());
        itemPrice.setText(String.valueOf(currentOffer.getPrice()));
        isForExchange.setText(currentOffer.getIsForExchange() ? "Tak" : "Nie");
        category.setText(currentOffer.getItemCategory());
        seller.setText(currentOffer.getSeller());
        description.setText(currentOffer.getItemDescription());
    }
}
