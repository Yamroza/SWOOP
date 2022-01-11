package GUI;

import Classes.Offer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class OfferListElement extends ListCell<Offer> {

    @FXML
    Text itemName;

    @FXML
    AnchorPane itemCell;

    @FXML
    Text itemPrice;

    @FXML
    Text itemExchange;

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
        }

        setText(null);
        setGraphic(itemCell);
    }

}
