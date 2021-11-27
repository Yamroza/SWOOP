package Classes;

public class Offer {
    private String name;
    private String description;
    private String category;
    public Boolean isItemForExchange;
    public Boolean isMoney;
    private Float price;
    private User seller;

    public enum status {
        active, inactive
    }

    public status offerStatus;


    // photo

    // if offer is money
    public Offer(float price) {
        this.isMoney = true;
        this.price = price;
        this.offerStatus = status.active;
    }

    // if offer is item
    public Offer(String name, String description, String itemCategory) {
        this.name = name;
        this.description = description;
        this.category = itemCategory;
        this.isMoney = false;
        this.isItemForExchange = true;
        this.offerStatus = status.active;
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
        this.isItemForExchange = false;
    }
}
