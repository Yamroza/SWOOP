package Database;

import Classes.Offer;
import Classes.Transaction;
import Classes.User;

import java.util.ArrayList;
import java.util.Objects;

public class Transactions {

    static ArrayList<Transaction> transactionsList = new ArrayList<>();
    static Transaction completedTransaction;

    public static ArrayList<Transaction> getUsersList() {
        return transactionsList;
    }

   /* public static User checkIfUserIsInBase(String givenUsername) {
        for (User user : usersList) {
            if (Objects.equals(user.getUsername(), givenUsername)) {
                return user;
            }
        }
        return null;
    } */

    public static void addTransaction(User seller, Offer offer, User buyer, Offer buyersOffer) {
        transactionsList.add(new Transaction(seller, offer, buyer, buyersOffer));
    }

    public static void setCompletedTransaction(Transaction transaction) {
        completedTransaction = transaction;
    }

    public static Transaction getCompletedTransaction() {
        return completedTransaction;
    }
}