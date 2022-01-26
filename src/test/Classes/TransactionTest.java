package Classes;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TransactionTest extends TestCase {

    public void testGetSellersOfferID() {
        Transaction tr = new Transaction(32, "Marcinek", "złamany grosz", 0);
        Assert.assertEquals(32, tr.getSellersOfferID());
    }

    public void testGetBuyer() {
        Transaction tr = new Transaction(32, "Marcinek", "złamany grosz", 0);
        Assert.assertEquals("Marcinek", tr.getBuyer());
    }

    public void testGetBuyersOffer() {
        Transaction tr = new Transaction(32, "Marcinek", "złamany grosz", 0);
        Assert.assertEquals("złamany grosz", tr.getBuyersOffer());
    }

    public void testGetSellersItem() {
        Transaction tr = new Transaction(32, "Marcinek",
                "złamany grosz", 0, 11, "Kordianek", "piłeczka");
        Assert.assertEquals("piłeczka", tr.getSellersItem());
    }

    public void testGetSeller() {
        Transaction tr = new Transaction(32, "Marcinek",
                "złamany grosz", 0, 11, "Kordianek", "piłeczka");
        Assert.assertEquals("Kordianek", tr.getSeller());
    }

    public void testGetTransactionID() {
        Transaction tr = new Transaction(32, "Marcinek",
                "złamany grosz", 0, 11, "Kordianek", "piłeczka");
        Assert.assertEquals(11, tr.getTransactionID());
    }

    public void testGetStatus() {
        Transaction tr = new Transaction(32, "Marcinek",
                "złamany grosz", 0, 11, "Kordianek", "piłeczka");
        Assert.assertEquals(0, tr.getStatus());
    }
}