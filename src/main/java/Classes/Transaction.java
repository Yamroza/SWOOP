package Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {
    private User seller;
    private Offer sellersOffer;
    private LocalDate startTransactionDate;
    private LocalDate endTransactionDate;

    private User buyer;
    private Offer buyersOffer;

    private enum Status {
        completed, in_progress, failed
    }

    private Status transactionStatus;


    public Transaction(User seller, Offer sellerOffer, User buyer, Offer buyersOffer,  LocalDate startDate) {
        this.seller = seller;
        this.sellersOffer = sellerOffer;
        this.buyer = buyer;
        this.buyersOffer = buyersOffer;
        this.startTransactionDate = startDate;
        this.transactionStatus = Status.in_progress;
    }

    public User getSeller() {
        return seller;
    }

    public Offer getSellersOffer() {
        return sellersOffer;
    }

    public User getBuyer() {
        return buyer;
    }

    public Offer getBuyersOffer() {
        return buyersOffer;
    }

    public LocalDate getStartTransactionDate() {
        return startTransactionDate;
    }

    public LocalDate getEndTransactionDate() {
        return endTransactionDate;
    }

    public void accept() {
        this.transactionStatus = Status.completed;
    }

    public void decline() {
        this.transactionStatus = Status.failed;
    }
}
