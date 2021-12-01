package Database;

import java.util.ArrayList;
import java.util.Objects;

import Classes.Offer;
import Classes.User;

public class Offers {
    static ArrayList<Offer> offerList = new ArrayList<>();

    public void addOffer(Offer newOffer) {
        offerList.add(newOffer);
    }

    public static void addOffer(int offer_id, String itemName, String itemDescription, String itemCategory,
                                Boolean isForExchange, Boolean isForSale, Float price, User seller) {

        Offer newOffer = new Offer(offer_id, itemName, itemDescription, itemCategory, isForExchange, isForSale, price, seller);
        offerList.add(newOffer);
    }

    public ArrayList<Offer> getOffers(User seller) {
        ArrayList<Offer> sellersOffers = new ArrayList<>();
        for (Offer offer : offerList) {
            if (Objects.equals(offer.getSeller(), seller)) {
                sellersOffers.add(offer);
            }
        }
        return sellersOffers;
    }

    public ArrayList<Offer> getOffersByUsername(String sellerUsername) {
        ArrayList<Offer> sellersOffers = new ArrayList<>();
        for (Offer offer : offerList) {
            if (Objects.equals(offer.getSeller().getLogin(), sellerUsername)) {
                sellersOffers.add(offer);
            }
        }
        return sellersOffers;
    }

    public ArrayList<Offer> getOffersByCategory(String category) {
        ArrayList<Offer> sellersOffers = new ArrayList<>();
        for (Offer offer : offerList) {
            if (Objects.equals(offer.getItemCategory(), category)) {
                sellersOffers.add(offer);
            }
        }
        return sellersOffers;
    }

    public ArrayList<Offer> getOffersBetween(int minPrice, int maxPrice) {
        ArrayList<Offer> sellersOffers = new ArrayList<>();
        for (Offer offer : offerList) {
            float price = offer.getPrice();
            if (price >= minPrice && price <= maxPrice) {
                sellersOffers.add(offer);
            }
        }
        return sellersOffers;
    }
}
