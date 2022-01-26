package Database;

import Classes.Offer;
import junit.framework.Assert;
import junit.framework.TestCase;
import junit.framework.Assert;
import org.junit.Test;
import Classes.Offer;
import Database.Offers;
import java.util.*;
import javafx.collections.ObservableList;
import java.sql.SQLException;

public class OffersTest extends TestCase {

    public void testGetNumOfOffers() throws SQLException {
        int size = Offers.getNumOfOffers();
        Assert.assertEquals(19, size);
    }

    public void testGetNumOfActiveOffers() throws SQLException {
        int active_size = Offers.getNumOfActiveOffers();
        Assert.assertEquals(12, active_size);
    }

    public void testUpdateOffer() throws SQLException {
        Offer new_offer = new Offer("deskorolka", "znudzio mi się", "Sport", true, true, 178.00f, "Jan", 0, "Warszawa", "mazowieckie", "https://i.imgur.com/Moe5dXk.jpg");

        new_offer.setOfferId(200);

        int size = Offers.getNumOfOffers();
        int active_size = Offers.getNumOfActiveOffers();

        Offers.addOfferToDatabase(new_offer);

        int new_size = Offers.getNumOfOffers();
        int new_active_size = Offers.getNumOfActiveOffers();

        System.out.println(new_size + " " + size);

        Assert.assertEquals(1, new_size - size);
        Assert.assertEquals(1, new_active_size - active_size);

        Offers.deleteOfferFromDatabase("deskorolka");

        new_size = Offers.getNumOfOffers();
        new_active_size = Offers.getNumOfActiveOffers();

        Assert.assertEquals(0, new_size - size);
        Assert.assertEquals(0, new_active_size - active_size);
    }

    public void testGetOffersByCond() throws SQLException {
        List<String> categories = new ArrayList<String>();
        categories.add("Sport");
        ObservableList<Offer> result = Offers.getOffersByCond("", categories, 1, 1, 0, 999, "", "", "");

        List<String> names = new ArrayList<String>();
        result.forEach((r) -> {
            names.add(r.getItemName());
        });

        Assert.assertTrue(names.contains("Wędka"));
        Assert.assertTrue(names.contains("Mazda RX6"));
    }
}