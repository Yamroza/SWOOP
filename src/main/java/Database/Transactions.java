package Database;

import Classes.Offer;
import Classes.Transaction;
import Classes.User;

import java.util.ArrayList;
import java.util.Objects;

public class Transactions {

    static ArrayList<Transaction> transactionsList = new ArrayList<>();

    public static ArrayList<Transaction> getTransactionsList() {
        return transactionsList;
    }

    public static void addTransaction(User seller, Offer offer, User buyer, Offer buyersOffer) {
        transactionsList.add(new Transaction(seller, offer, buyer, buyersOffer));
    }

    /*
    * */

}