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

    public enum status {
        active, inactive
    }

    public Transaction(User startSeller, Offer startOffer, LocalDate startDate) {
        seller = startSeller;
        sellersOffer = startOffer;
        startTransactionDate = startDate;
    }



}
