package GUI;

import Classes.Offer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class TransactionListElement extends ListCell<Offer> {

    @FXML
    Text itemName;

    @FXML
    AnchorPane itemCell;

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
        else
        {
            if(loader == null)
            {
                loader = new FXMLLoader(App.class.getResource("/transactionListElement.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            itemName.setText(offer.getItemName());
        }

        setText(null);
        setGraphic(itemCell);
    }

}
