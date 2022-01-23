package GUI;

import Classes.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class UserTransactionListElement extends ListCell<Transaction> {

    @FXML
    Text userTransactionName;

    @FXML
    AnchorPane userTransactionCell;

    @FXML
    Text userTransactionOffer;

    @FXML
    Text userTransactionSeller;

    private FXMLLoader loader;

    @Override
    protected void updateItem(Transaction transaction, boolean empty) {
        super.updateItem(transaction, empty);
        if (empty || transaction == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (loader == null)
            {
                loader = new FXMLLoader(App.class.getResource("/userTransactionListCell.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (transaction.getStatus() == 0) {
                userTransactionName.setText(transaction.getSellersItem());
                userTransactionOffer.setText(transaction.getBuyersOffer());
                userTransactionSeller.setText("Zgłoszenie do: " + transaction.getSeller());
                userTransactionName.setStrikethrough(false);
                userTransactionOffer.setStrikethrough(false);
                userTransactionSeller.setStrikethrough(false);
                userTransactionName.setFill(Color.BLACK);
                userTransactionOffer.setFill(Color.BLACK);
                userTransactionSeller.setFill(Color.BLACK);
            }
            else if (transaction.getStatus() == 1) {
                userTransactionName.setText(transaction.getSellersItem());
                userTransactionOffer.setText(transaction.getBuyersOffer());
                userTransactionSeller.setText("Zgłoszenie do: " + transaction.getSeller());
                userTransactionName.setStrikethrough(false);
                userTransactionOffer.setStrikethrough(false);
                userTransactionSeller.setStrikethrough(false);
                userTransactionName.setFill(Color.LIMEGREEN);
                userTransactionOffer.setFill(Color.LIMEGREEN);
                userTransactionSeller.setFill(Color.LIMEGREEN);
            } else if (transaction.getStatus() == -1) {
                userTransactionName.setText(transaction.getSellersItem());
                userTransactionOffer.setText(transaction.getBuyersOffer());
                userTransactionSeller.setText("Zgłoszenie do: " + transaction.getSeller());
                userTransactionName.setStrikethrough(true);
                userTransactionOffer.setStrikethrough(true);
                userTransactionSeller.setStrikethrough(true);
                userTransactionName.setFill(Color.RED);
                userTransactionOffer.setFill(Color.RED);
                userTransactionSeller.setFill(Color.RED);
            }
        }
        setText(null);
        setGraphic(userTransactionCell);
    }
}
