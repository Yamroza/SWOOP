package Classes;

public class Item {
    String itemName;
    String itemDescription;
    String itemCategory;
    Boolean isItemForExchange;
    Float price;
    User seller;
    // foto
    // enum od stanu transakcji


    public Item(String itemName, String itemDescription, String itemCategory) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
    }
}
