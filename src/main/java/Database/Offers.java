package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import Classes.Offer;
import Classes.Transaction;
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
        int status = rs.getInt("offer_status");
        offer.setStatus(status);
//        if(status == 1)
//        {
//            offer.setBuyer(rs.getString("buyer"));
//        }
        return offer;
    }

    private static String generateInsert(Offer offer){
        int isForSale = offer.getIsForSale() ? 1 : 0;
        int isForExchange = offer.getIsForExchange() ? 1 : 0;
        return ("INSERT INTO OFFERS (offer_id, name, description, category, for_exchange, for_sale, price, seller) " +
                "VALUES ('" + offer.getOfferId() + "' , '" + offer.getItemName()  + "' , '" + offer.getItemDescription()
                + "' , '" + offer.getItemCategory() + "' , '" + isForExchange + "' , '" + isForSale + "' , '" +
                offer.getPrice() + "' , '" + offer.getSeller() + "'" + offer.getStatus() + ")");
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
        if (conn != null) {
            Statement stmt;
            stmt = conn.createStatement();
            try {
                ResultSet rs = stmt.executeQuery("select * from offers where offer_status = 0 " +
                        "order by OFFER_ID desc fetch first 10 rows only");
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

    public static ObservableList<Offer> getOffersByCond(String name, List<String> categories,
                                                        int is_exchange, int is_for_sale,
                                                       int price_from, int price_to, String city,
                                                        String sorting) throws SQLException {
        String query = "select * from offers where";
        if(is_exchange == is_for_sale){
            query +=  " 1=1"; // anything
        }
        else if (is_exchange == 1){
            query += " for_exchange = 1";
        } else if (is_for_sale == 1) {
            query += " for_sale = 1";
        }
        if(!name.isEmpty()) {
            query += " and name like '" + name +"'";
        }
        if(categories.size() > 0) {
            query += " and category in (" + "'" + String.join("','", categories) + "'" + ")";
        }
        if(price_from >= 0 && price_to >= 0) {
            query += " and price between " + price_from + ".00 and " + price_to + ".00";
        }
        switch (sorting) {
            case "alfabetycznie" -> query += " order by name";
            case "według cen - rosnąco" -> query += " order by price asc";
            case "według cen - malejąco" -> query += " order by price desc";
            default -> query += " order by OFFER_ID desc";
        }
        return getOffersByQuery(query);
    }

    public static ObservableList<Offer> getOffersByQuery(String query) throws SQLException {
        Connecting DB = new Connecting();
        Connection conn = DB.getConn();
        Offer nextOffer;
        ObservableList<Offer> offers = FXCollections.observableArrayList();
        if (conn != null) {
            Statement stmt;
            stmt = conn.createStatement();
            try {
                ResultSet rs = stmt.executeQuery(query);
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

    public static ObservableList<Offer> getNextTenUserOffers(String login) throws SQLException {
        String q = "select O.*, S.BUYER from offers O LEFT JOIN (" +
                " SELECT T.OFFER_ID, T.BUYER FROM TRANSACTIONS T" +
                " WHERE T.STATUS = 1 ) S on O.OFFER_ID = S.OFFER_ID" +
                " where O.SELLER like '" + login + "' AND O.OFFER_STATUS IN(0, 1)" +
                " order by O.OFFER_ID desc fetch first 10 rows only";

        return getOffersByQuery(q);
    }

    private static String generateStatusChange(int offerID, int s)
    {
        return ("UPDATE OFFERS SET OFFER_STATUS = " + s  + " WHERE OFFER_ID = " + offerID);
    }

    public static void addOfferToDatabase(Offer offer) throws SQLException {
        Connecting DB = new Connecting();
        DB.alterTable(generateInsert(offer));
        DB.close();
    }


    public static void setOfferStatus(int offerID, int status) throws SQLException {
        Connecting DB = new Connecting();
        DB.alterTable(generateStatusChange(offerID, status));
        DB.close();
    }
}
