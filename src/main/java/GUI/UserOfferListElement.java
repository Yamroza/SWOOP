package GUI;

import Classes.Offer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class UserOfferListElement extends ListCell<Offer> {

    @FXML
    Text userOfferName;

    @FXML
    AnchorPane userOfferCell;

    @FXML
    Text userOfferBuyer;

    private FXMLLoader loader;

    @Override
    protected void updateItem(Offer offer, boolean empty)
    {
        super.updateItem(offer, empty);
        if (empty || offer == null)
        {
            setText(null);
            setGraphic(null);
        }
        else {
            if (loader == null) {
                loader = new FXMLLoader(App.class.getResource("/userOfferListCell.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (offer.getStatus() == 0) {
                userOfferName.setText(offer.getItemName());
                userOfferName.setFill(Color.BLACK);
                userOfferBuyer.setVisible(false);
            } else if (offer.getStatus() == 1) {
                userOfferName.setText(offer.getItemName());
                userOfferName.setFill(Color.LIMEGREEN);
                userOfferBuyer.setVisible(true);
                userOfferBuyer.setText("Kupił: " + offer.getBuyer());
                userOfferBuyer.setFill(Color.LIMEGREEN);
            }
        }
        setText(null);
        setGraphic(userOfferCell);
    }
}
