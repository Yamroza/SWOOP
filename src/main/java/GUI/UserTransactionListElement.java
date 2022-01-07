package GUI;

import Classes.Transaction;
import Database.Transactions;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

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
            if (loader == null) {
                loader = new FXMLLoader(App.class.getResource("/userTransactionListCell.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                {
                    userTransactionName.setText(transaction.getSellersItem());
                    userTransactionOffer.setText(transaction.getBuyersOffer());
                    userTransactionSeller.setText("Zgłoszenie do: " + transaction.getSeller());
                    if (transaction.getStatus() == 1) {
                        userTransactionName.setFill(Color.LIMEGREEN);
                        userTransactionOffer.setFill(Color.LIMEGREEN);
                        userTransactionSeller.setFill(Color.LIMEGREEN);
                    } else if (transaction.getStatus() == -1) {
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
    }
}
