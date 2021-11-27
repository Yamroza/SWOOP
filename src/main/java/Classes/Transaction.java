package Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {
    private User seller;
    private Offer sellersOffer;
    private LocalDate startTransactionDate;
    private LocalDate endTransactionDate;

    public User buyer;
    public Offer buyersOffer;


    public Transaction(User seller, Offer sellerOffer, User buyer, Offer buyersOffer,  LocalDate startDate) {
        this.seller = seller;
        this.sellersOffer = sellerOffer;
        this.buyer = buyer;
        this.buyersOffer = buyersOffer;
        this.startTransactionDate = startDate;
    }

}
