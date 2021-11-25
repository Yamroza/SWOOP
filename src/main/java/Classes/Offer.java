package Classes;

public class Offer {
    String itemName;
    String itemDescription;
    String itemCategory;
    Boolean isItemForExchange;
    Float price;
    User seller;
    // foto

    public Offer(String itemName, String itemDescription, String itemCategory) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
    }
}
