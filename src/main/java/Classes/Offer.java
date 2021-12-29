package Classes;

import Database.Connecting;

import java.sql.SQLException;

public class Offer {

    int offer_id;
    String itemName;
    String itemDescription;
    String itemCategory;
    Boolean isForExchange;
    Boolean isForSale;
    Float price;
    User seller;
    // photo

    public Offer(String itemName, String itemDescription, String itemCategory, Boolean isForExchange,
                 Boolean isForSale, Float price, User seller) {}

    public Offer(int offer_id, String itemName, String itemDescription, String itemCategory,
                 Boolean isForExchange, Boolean isForSale, Float price, User seller) {
        this.offer_id = offer_id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
        this.isForExchange = isForExchange;
        this.isForSale = isForSale;
        this.price = price;
        this.seller = seller;
    }

    public Offer()
    {
        this.offer_id = -1;
        this.itemName = null;
        this.itemDescription = null;
        this.itemCategory = null;
        this.isForExchange = null;
        this.isForSale = null;
        this.price = null;
        this.seller = null;
    }
    public int getOfferId() {
        return offer_id;
    }

    public void setOfferId(int offer_id) {
        this.offer_id = offer_id;
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

    public Boolean getIsForExchange() {
        return isForExchange;
    }

    public void setIsForExchange(Boolean isForExchange) {
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

    public int isForExchangeInt() {
        if (isForExchange) {return 1;}
        else {return 0;}
    }

    public int isForSaleInt() {
        if (isForSale) {return 1;}
        else {return 0;}
    }

    public String generate_insert(){
        return ("INSERT INTO OFFERS (offer_id, name, description, category, for_exchange, for_sale, price, seller) " +
                "VALUES (" + offer_id + " , '" + itemName  + "' , '" + itemDescription + "' , '" + itemCategory + "' , " +
                this.isForExchangeInt() + " , "  + this.isForSaleInt() + " , " + price + " , '" + seller.getName() + "'" + ")");
    }

    public void addToDatabase() throws SQLException {
        Connecting DB = new Connecting();
        DB.alterTable(this.generate_insert());
        DB.close();
    }
}
