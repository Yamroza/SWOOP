package GUI;

import Classes.Offer;
import Classes.Transaction;
import Database.Transactions;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

import static java.util.Collections.copy;

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

    @FXML
    private void declineClicked() throws SQLException {
        Transactions.setTransactionDeclined(this.getItem());
        getListView().getItems().remove(this.getItem());
        ObservableList<Transaction> tmpList = getListView().getItems();
        getListView().getItems().setAll(tmpList);
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
            transactionBuyer.setText("Zgłoszenie od: " + transaction.getBuyer());
            acceptButton.setOnAction(arg0 -> {
                try {
                    Transactions.setTransactionAccepted(getItem());
                    int offerId = getItem().getSellersOfferID();
                    getListView().getItems().removeIf(transaction1 -> transaction1.getSellersOfferID() == offerId);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    App.setRoot("accountScreen");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                App.myStage.setScene(App.scene);
                App.myStage.sizeToScene();
            });
            declineButton.setOnAction(arg0 -> {
                try {
                    Transactions.setTransactionDeclined(getItem());
                    getListView().getItems().remove(getItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    App.setRoot("accountScreen");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                App.myStage.setScene(App.scene);
                App.myStage.sizeToScene();
            });
        }

        setText(null);
        setGraphic(transactionCell);
    }

}
