package Classes;

import Database.Connecting;

import java.sql.SQLException;
import java.time.LocalDate;

public class Transaction {
    private User seller;
    private Offer sellersOffer;
    private LocalDate transactionDate;
    public User buyer;
    public Offer buyersOffer;

    private enum Status {
        completed, in_progress, failed
    }


    public Transaction() {
        transactionDate = LocalDate.now();
    }

    public Transaction(User seller, Offer offer, User buyer, Offer buyersOffer) {
        this.seller = seller;
        this.sellersOffer = offer;
        this.buyer = buyer;
        this.buyersOffer = buyersOffer;
        transactionDate = LocalDate.now();
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Offer getSellersOffer() {
        return sellersOffer;
    }

    public void setSellersOffer(Offer sellersOffer) {
        this.sellersOffer = sellersOffer;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate startTransactionDate) {
        this.transactionDate = startTransactionDate;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Offer getBuyersOffer() {
        return buyersOffer;
    }

    public void setBuyersOffer(Offer buyersOffer) {
        this.buyersOffer = buyersOffer;
    }

    public String generate_insert(){
        return ("INSERT INTO TRANSACTIONS (seller, seller_offer, buyer, buyers_offer) " +
                "VALUES ('" + seller.getName() + "' , '" + sellersOffer.getItemName() + "' , '"
                + buyer.getName() + "' , '" + buyersOffer.getItemName() + "'" + ")");
    }

    public void addToDatabase() throws SQLException {
        Connecting DB = new Connecting();
        DB.alterTable(this.generate_insert());
        DB.close();
    }

}
