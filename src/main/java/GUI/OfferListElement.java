package GUI;

import Classes.Offer;
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
                itemPrice.setText(offer.getPrice() + "PLN");
            }
            URL url = null;
            try {
                url = new URL(offer.getPhoto());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            File file = new File("work_image.jpg");
            int connectionTimeout = 10 * 1000; // 10 sec
            int readTimeout = 300 * 1000; // 3 min
            try {
                FileUtils.copyURLToFile(url, file, connectionTimeout, readTimeout);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image image = new Image(file.toURI().toString());
            itemPhoto.setImage(image);
        }

        setText(null);
        setGraphic(itemCell);
    }

}
