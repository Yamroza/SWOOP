package Classes;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
    String username;
    String password;
    String name;
    String surname;
    LocalDate birthDate;
    // profile pic
    // ArrayList Item albo pointer/referencja/cokolwiek to tu jest na Item'
    List<Offer> offers;
    LocalDateTime accountCreationTime;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.accountCreationTime = LocalDateTime.now();
        this.offers = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getAccountCreationTime() {
        return accountCreationTime;
    }

    public void addOffer(String name, String description, String itemCategory, Boolean isForExchange, Boolean isForSale) {
        Offer newOffer = new Offer(name, description, itemCategory, isForExchange, isForSale);
        offers.add(newOffer);
    }
}
