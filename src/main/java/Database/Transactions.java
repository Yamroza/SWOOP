package Database;

import Classes.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Transactions {

    private static Transaction returnTransaction(ResultSet rs) throws SQLException {
        int offerID = rs.getInt("offer_id");
        String sellersItem = rs.getString("item_name");
        String buyer = rs.getString("buyer");
        String buyersOffer = rs.getString("buyers_offer");
        int status = rs.getInt("status");
        return new Transaction(offerID, buyer, buyersOffer, status, sellersItem);
    }

    public static ObservableList<Transaction> getUserTransactions(String userLogin) throws SQLException {
        Connecting DB = new Connecting();
        Connection conn = DB.getConn();
        Transaction nextTransaction;
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        if (conn != null) {
            Statement stmt;
            stmt = conn.createStatement();
            try {
                ResultSet rs = stmt.executeQuery("Select T.*, o.NAME as item_name from TRANSACTIONS T " +
                                "Join OFFERS o on o.OFFER_ID = T.OFFER_ID " +
                        "Where o.SELLER Like '" + userLogin + "' ");
                while (rs.next()) {
                    nextTransaction = returnTransaction(rs);
                    transactions.add(nextTransaction);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        DB.close();
        return transactions;
    }

    private static String generate_insert(Transaction t){
        return ("INSERT INTO TRANSACTIONS (offer_id, buyer, buyers_offer, status) " +
                "VALUES ('" + t.getSellersOfferID()+ "' , '" + t.getBuyer()+
                "' , '" + t.getBuyersOffer() + "' , '" + t.getStatus() + "'" + ")");
    }

    public static void addTransactionToDatabase(Transaction transaction) throws SQLException {
        Connecting DB = new Connecting();
        DB.alterTable(generate_insert(transaction));
        DB.close();
    }
}