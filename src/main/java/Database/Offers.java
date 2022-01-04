package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Classes.Offer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Offers {

    static Offer selectedOffer;

    public static Offer getSelectedOffer() {
        return selectedOffer;
    }

    public static void setSelectedOffer(Offer selectedOffer) {
        Offers.selectedOffer = selectedOffer;
    }

    private static Offer returnOffer(ResultSet rs) throws SQLException {
        Offer offer = new Offer();
        offer.setOfferId(rs.getInt("offer_id"));
        offer.setItemName(rs.getString("name"));
        offer.setItemDescription(rs.getString("description"));
        offer.setItemCategory(rs.getString("category"));
        boolean isForExchange = (rs.getInt("for_exchange") == 1);
        offer.setIsForExchange(isForExchange);
        boolean isForSale = (rs.getInt("for_sale") == 1);
        offer.setIsForSale(isForSale);
        if(isForSale)
        {
            offer.setPrice(rs.getFloat("price"));
        }
        offer.setSeller(rs.getString("seller"));
        return offer;
    }

    private static String generateInsert(Offer offer){
        int isForSale = offer.getIsForSale() ? 1 : 0;
        int isForExchange = offer.getIsForExchange() ? 1 : 0;
        return ("INSERT INTO OFFERS (offer_id, name, description, category, for_exchange, for_sale, price, seller) " +
                "VALUES ('" + offer.getOfferId() + "' , '" + offer.getItemName()  + "' , '" + offer.getItemDescription()
                + "' , '" + offer.getItemCategory() + "' , '" + isForExchange + "' , '" + isForSale + "' , '" +
                offer.getPrice() + "' , '" + offer.getSeller() + "')");
    }

    public static int getNewOfferId() throws SQLException {
        Connecting DB = new Connecting();
        Connection conn = DB.getConn();
        int newID = -1;
        if (conn != null) {
            Statement stmt;
            stmt = conn.createStatement();
            try {
                ResultSet rs = stmt.executeQuery("Select Max(OFFER_ID) as max from OFFERS");
                while (rs.next()) {
                    newID = rs.getInt("max");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        DB.close();
        return newID + 1;
    }

    public static ObservableList<Offer> getNextTenOffers() throws SQLException {
        Connecting DB = new Connecting();
        Connection conn = DB.getConn();
        Offer nextOffer;
        ObservableList<Offer> offers = FXCollections.observableArrayList();
        int newID = -1;
        if (conn != null) {
            Statement stmt;
            stmt = conn.createStatement();
            try {
                ResultSet rs = stmt.executeQuery("select * from offers order by OFFER_ID desc fetch first 10 rows only");
                while (rs.next()) {
                    nextOffer = returnOffer(rs);
                    offers.add(nextOffer);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        DB.close();
        return offers;
    }

    public static void addOfferToDatabase(Offer offer) throws SQLException {
        Connecting DB = new Connecting();
        DB.alterTable(generateInsert(offer));
        DB.close();
    }
}
