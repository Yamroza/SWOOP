package GUI;

import Classes.Offer;
import Classes.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class TransactionListElement extends ListCell<Transaction> {

    @FXML
    Text transactionName;

    @FXML
    AnchorPane transactionCell;

    @FXML
    Text transactionOffer;

    @FXML
    Text transactionBuyer;

    private FXMLLoader loader;

    @FXML
    private void acceptClicked(){
        System.out.println("Tak");
    }

    @FXML
    private void declineClicked(){
        System.out.println("Nie");
    }

    @Override
    protected void updateItem(Transaction transaction, boolean empty)
    {
        super.updateItem(transaction, empty);
        if (empty || transaction == null)
        {
            setText(null);
            setGraphic(null);
        }
        else
        {
            if(loader == null)
            {
                loader = new FXMLLoader(App.class.getResource("/transactionListCell.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            transactionName.setText(transaction.getSellersItem());
            transactionOffer.setText(transaction.getBuyersOffer());
            transactionBuyer.setText(transaction.getBuyer());
        }


        setText(null);
        setGraphic(transactionCell);
    }

}
