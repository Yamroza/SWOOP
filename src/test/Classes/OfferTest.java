package Classes;

import junit.framework.Assert;
import junit.framework.TestCase;

public class OfferTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void testSetGetOfferId() {
        Offer off = new Offer();
        off.setOfferId(3);
        Assert.assertEquals(3, off.getOfferId());
    }

    public void testSetGetItemName() {
        Offer off = new Offer();
        off.setItemName("sznur");
        Assert.assertEquals("sznur", off.getItemName());
    }

    public void testSetGetItemDescription() {
        Offer off = new Offer();
        off.setItemDescription("do wieszania prania");
        Assert.assertEquals("do wieszania prania", off.getItemDescription());
    }

    public void testSetGetItemCategory() {
        Offer off = new Offer();
        off.setItemCategory("Sport");
        Assert.assertEquals("Sport", off.getItemCategory());
    }

    public void testSetGetIsForExchange() {
        Offer off = new Offer();
        off.setIsForExchange(true);
        Assert.assertTrue(off.isForExchange());
    }

    public void testSetGetIsForSale() {
        Offer off = new Offer();
        off.setIsForSale(true);
        Assert.assertTrue(off.isForSale());
    }

    public void testSetGetSeller() {
        Offer off = new Offer();
        off.setSeller("Miłośnik grzybów");
        Assert.assertEquals("Miłośnik grzybów", off.getSeller());
    }

    public void testSetGetStatus() {
        Offer off = new Offer();
        off.setStatus(1);
        Assert.assertEquals(1, off.getStatus());
    }

    public void testSetGetBuyer() {
        Offer off = new Offer();
        off.setBuyer("Grzesiu");
        Assert.assertEquals("Grzesiu", off.getBuyer());
    }

    public void testSetGetLocalisation() {
        Offer off = new Offer();
        off.setLocalisation("Marcinkowo");
        Assert.assertEquals("Marcinkowo", off.getLocalisation());
    }

    public void testSetGetVoivodship() {
        Offer off = new Offer();
        off.setVoivodship("kieleckie");
        Assert.assertEquals("kieleckie", off.getVoivodship());
    }
}