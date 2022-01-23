package Classes;


public class Transaction {
    private int transactionID;
    private final int sellersOfferID;
    private String seller;
    private String sellersItem;
    private final String buyer;
    private final String buyersOffer;
    private final int status;


    // creating transaction object without seller
    public Transaction(int offerID, String buyer, String buyersOffer, int status) {
        this.sellersOfferID = offerID;
        this.buyer = buyer;
        this.buyersOffer = buyersOffer;
        this.status = status;
    }

    // creating transaction object with seller
    public Transaction(int offerID, String buyer, String buyersOffer, int status, int transactionID, String seller,
                       String sellersItem) {
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

    public String getBuyer() {
        return buyer;
    }

    public String getBuyersOffer() {
        return buyersOffer;
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
