package Database;

import Classes.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Transactions {

    private static Transaction returnTransaction(ResultSet rs) throws SQLException {
        int offerID = rs.getInt("offer_id");
        String buyer = rs.getString("buyer");
        String buyersOffer = rs.getString("buyers_offer");
        int transactionID = rs.getInt("transaction_id");
        String seller = rs.getString("seller");
        String sellersItem = rs.getString("item_name");
        int status = rs.getInt("status");
        return new Transaction(offerID, buyer, buyersOffer, status, transactionID, seller, sellersItem);
    }

    public static ObservableList<Transaction> getTransactions(String userLogin) throws SQLException {
        Connecting DB = new Connecting();
        Connection conn = DB.getConn();
        Transaction nextTransaction;
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        if (conn != null) {
            Statement stmt;
            stmt = conn.createStatement();
            try {
                ResultSet rs = stmt.executeQuery("Select T.*, o.NAME as item_name, o.SELLER from TRANSACTIONS T " +
                                "Join OFFERS o on o.OFFER_ID = T.OFFER_ID " +
                        "Where o.SELLER = '" + userLogin + "' AND T.STATUS = 0 " +
                        "ORDER BY T.TRANSACTION_ID DESC");
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

    public static ObservableList<Transaction> getUserTransactions(String userLogin) throws SQLException {
        Connecting DB = new Connecting();
        Connection conn = DB.getConn();
        Transaction nextTransaction;
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        if (conn != null) {
            Statement stmt;
            stmt = conn.createStatement();
            try {
                ResultSet rs = stmt.executeQuery("Select T.*, o.NAME as item_name, o.SELLER from TRANSACTIONS T " +
                        "Join OFFERS o on o.OFFER_ID = T.OFFER_ID " +
                        "Where T.BUYER = '" + userLogin + "' " +
                        "ORDER BY T.TRANSACTION_ID DESC");
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

    private static String generateAccept(Transaction t)
    {
        return("UPDATE TRANSACTIONS SET STATUS = " +
                "CASE TRANSACTION_ID " +
                "WHEN " + t.getTransactionID() + " THEN 1" +
                "ELSE -1 END WHERE OFFER_ID = " + t.getSellersOfferID());
    }

    private static String generateDecline(Transaction t)
    {
        return ("UPDATE TRANSACTIONS SET STATUS = -1 WHERE TRANSACTION_ID = " + t.getTransactionID());
    }

    public static void addTransactionToDatabase(Transaction transaction) throws SQLException {
        Connecting DB = new Connecting();
        DB.alterTable(generate_insert(transaction));
        DB.close();
    }

    public static void setTransactionDeclined(Transaction transaction) throws SQLException {
        Connecting DB = new Connecting();
        DB.alterTable(generateDecline(transaction));
        DB.close();
    }

    public static void setTransactionAccepted(Transaction transaction) throws SQLException {
        Connecting DB = new Connecting();
        DB.alterTable(generateAccept(transaction));
        DB.close();
    }
}