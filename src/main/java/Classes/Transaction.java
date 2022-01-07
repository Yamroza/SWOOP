package Classes;

import Database.Connecting;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private int transactionID;
    private int sellersOfferID;
    private String seller;
    private String sellersItem;
    private String buyer;
    private String buyersOffer;
    private int status;




    public Transaction(int offerID, String buyer, String buyersOffer) {
        this.sellersOfferID = offerID;
        this.buyer = buyer;
        this.buyersOffer = buyersOffer;
        this.status = 0;
    }

    public Transaction(int offerID, String buyer, String buyersOffer, int transactionID, String seller,
                       String sellersItem, int status) {
        this.transactionID = transactionID;
        this.sellersOfferID = offerID;
        this.seller = seller;
        this.sellersItem = sellersItem;
        this.buyer = buyer;
        this.buyersOffer = buyersOffer;
        this.status = status;
    }

    public int getSellersOfferID() {
        return sellersOfferID;
    }

    public void setSellersOfferID(int sellersOffer) {
        this.sellersOfferID = sellersOffer;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuyersOffer() {
        return buyersOffer;
    }

    public void setBuyersOffer(String buyersOffer) {
        this.buyersOffer = buyersOffer;
    }

    public String getSellersItem() {
        return sellersItem;
    }

    public String getSeller() {
        return seller;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public int getStatus() {
        return status;
    }
}
