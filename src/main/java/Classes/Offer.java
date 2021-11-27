package Classes;

public class Offer {
    private String name;
    private String description;
    private String category;
    public Boolean isForExchange;
    public Boolean isForSale;
    public Boolean isMoney;
    private Float price;
    private User seller;

    private enum Status {
        active, inactive
    }

    private Status offerStatus;


    // photo

    // if offer is money
    public Offer(float price) {
        this.isMoney = true;
        this.price = price;
        this.offerStatus = Status.active;
    }

    // if offer is item
    public Offer(String name, String description, String itemCategory, Boolean isForExchange, Boolean isForSale) {
        this.name = name;
        this.description = description;
        this.category = itemCategory;
        this.isMoney = false;
        this.isForExchange = isForExchange;
        this.isForSale = isForSale;
        this.offerStatus = Status.active;
    }

    public String getName() {
        if (this.isMoney) {
            return String.valueOf(this.price);
        }
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public Float getPrice() {
        return price;
    }

    public void hideOffer() {
        this.offerStatus = Status.inactive;
    }
}
