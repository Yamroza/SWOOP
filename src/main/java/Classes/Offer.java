package Classes;

public class Offer {
    String itemName;
    String itemDescription;
    String itemCategory;
    Boolean isForExchange;
    Boolean isForSale;
    Float price;
    User seller;
    // photo

    public Offer() {}

    public Offer(String itemName, String itemDescription, String itemCategory,
                 Boolean isForExchange, Boolean isForSale, Float price, User seller) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
        this.isForExchange = isForExchange;
        this.isForSale = isForSale;
        this.price = price;
        this.seller = seller;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public Boolean getItemForExchange() {
        return isForExchange;
    }

    public void setItemForExchange(Boolean isForExchange) {
        this.isForExchange = isForExchange;
    }

    public Boolean getIsForSale() {
        return this.isForSale;
    }

    public void setIsForSale(Boolean isForSale) {
        this.isForSale = isForSale;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
