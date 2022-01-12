package GUI;

import Classes.Offer;
import Database.Imgur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class OfferListElement extends ListCell<Offer> {

    @FXML
    Text itemName;

    @FXML
    AnchorPane itemCell;

    @FXML
    Text itemPrice;

    @FXML
    Text itemExchange;

    @FXML
    ImageView itemPhoto;

    private FXMLLoader loader;

    @Override
    protected void updateItem(Offer offer, boolean empty)
    {
        super.updateItem(offer, empty);
        if (empty || offer == null)
        {
            setText("");
            setGraphic(null);
            //super.getListView().getItems().remove(this);
            if(this.itemCell != null) {
                itemCell.setVisible(false);
            }

        }
        else
        {
            if(loader == null)
            {
                loader = new FXMLLoader(App.class.getResource("/offerListElement.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            itemCell.setVisible(true);
            itemName.setText(offer.getItemName());
            itemExchange.setVisible(false);
            itemPrice.setVisible(false);
            if(offer.getIsForExchange())
            {
                itemExchange.setVisible(true);
            }
            if(offer.getIsForSale())
            {
                itemPrice.setVisible(true);
                itemPrice.setText("Cena: " + offer.getPrice() + " pln");
            }
            Image image = null;
            try {
                image = Imgur.showImageFromLink(offer.getPhoto());
            } catch (IOException e) {
                e.printStackTrace();
            }
            itemPhoto.setImage(image);
        }

        setText(null);
        setGraphic(itemCell);
    }

}
