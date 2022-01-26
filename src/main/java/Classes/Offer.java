package Classes;

public class Offer {

    int offer_id;
    String itemName;
    String itemDescription;
    String itemCategory;
    Boolean isForExchange;
    Boolean isForSale;
    Float price;
    String seller;
    String buyer;
    int status;
    String localisation;
    String photo;
    String voivodship;

    // creating offer object with specified photo
    public Offer(String itemName, String itemDescription, String itemCategory, Boolean isForExchange,
                 Boolean isForSale, Float price, String seller, int status, String localisation, String voivodship, String photo) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
        this.isForExchange = isForExchange;
        this.isForSale = isForSale;
        this.price = price;
        this.seller = seller;
        this.status = status;
        this.localisation = localisation;
        this.voivodship = voivodship;
        this.photo = photo;
    }

    // creating offer object without specified photo
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
        this.localisation = null;
        this.voivodship = null;
        this.photo = "https://i.imgur.com/Moe5dXk.jpg";
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

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getLocalisation() { return localisation; }

    public void setLocalisation(String localisation) { this.localisation = localisation; }

    public String getPhoto() { return photo; }

    public void setPhoto(String photo) { this.photo = photo; }

    public String getVoivodship() { return voivodship; }

    public void setVoivodship(String voivodship) { this.voivodship = voivodship; }

    public boolean isForExchange() {
        return this.isForExchange;
    }

    public boolean isForSale() {
        return this.isForSale;
    }
}
