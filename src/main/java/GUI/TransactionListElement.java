package GUI;

import Classes.Transaction;
import Database.Offers;
import Database.Transactions;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class TransactionListElement extends ListCell<Transaction> {

    @FXML
    Text transactionName;

    @FXML
    AnchorPane transactionCell;

    @FXML
    Text transactionOffer;

    @FXML
    Text transactionBuyer;

    @FXML
    Button acceptButton;

    @FXML
    Button declineButton;

    private FXMLLoader loader;

    @Override
    protected void updateItem(Transaction transaction, boolean empty)
    {
        super.updateItem(transaction, empty);
        if (empty || transaction == null)
        {
            setText(null);
            setGraphic(null);
            if(transactionCell != null) {
                transactionCell.setVisible(false);
            }
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
            transactionCell.setVisible(true);
            transactionName.setText(transaction.getSellersItem());
            transactionOffer.setText(transaction.getBuyersOffer());
            transactionBuyer.setText("Zgłoszenie od: " + transaction.getBuyer());
            acceptButton.setOnAction(arg0 -> {
                try {
                    Transactions.setTransactionAccepted(getItem());
                    int offerID = getItem().getSellersOfferID();
                    getListView().getItems().removeIf(transaction1 -> transaction1.getSellersOfferID() == offerID);
                    Offers.setOfferStatus(offerID, 1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                this.getListView().refresh();
            });
            declineButton.setOnAction(arg0 -> {
                try {
                    Transactions.setTransactionDeclined(getItem());
                    getListView().getItems().remove(getItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                this.getListView().refresh();
            });
        }
        setText(null);
        setGraphic(transactionCell);
    }
}
