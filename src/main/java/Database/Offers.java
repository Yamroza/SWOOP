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
        offer.setLocalisation(rs.getString("localisation"));
        offer.setPhoto(rs.getString("photo"));
        offer.setVoivodship("voivodship");
        offer.setLocalisation("localisation");
        if(status == 1)
        {
            offer.setBuyer(rs.getString("buyer"));
        }
        return offer;
    }

    private static String generateInsert(Offer offer){
          int isForSale = offer.getIsForSale() ? 1 : 0;
          int isForExchange = offer.getIsForExchange() ? 1 : 0;
          return ("INSERT INTO OFFERS (name, description, category, for_exchange, for_sale, price, seller, offer_status, localisation, photo) " +
              "VALUES ('" + offer.getItemName()  + "' , '" + offer.getItemDescription()
              + "' , '" + offer.getItemCategory() + "' , " + isForExchange + " , " + isForSale + " , " +
              offer.getPrice() + " , '" + offer.getSeller() + "'," + offer.getStatus() + ",'" + offer.getLocalisation() + "', '" + offer.getPhoto() + "')");
  }

    public static ObservableList<Offer> getNextTenOffers() throws SQLException {
        return getOffersByQuery("select * from offers where offer_status = 0 " +
                "order by OFFER_ID desc");
    }

    public static ObservableList<Offer> getOffersByCond(String name, List<String> categories,
                                                        int is_exchange, int is_for_sale,
                                                        int price_from, int price_to, String voivodship, String city,
                                                        String sorting) throws SQLException {
        String query = "select * from offers where offer_status = 0 and";
        if(is_exchange == is_for_sale){
            query +=  " 1=1"; // anything
        }
        else if (is_exchange == 1){
            query += " for_exchange = 1";
        } else if (is_for_sale == 1) {
            query += " for_sale = 1";
        }
        if(!name.isEmpty()) {
            query += " and LOWER(name) like('%" + name.toLowerCase() +"%')";
        }
        if(!city.isEmpty()) {
            query += " and localisation like('" + city +"')";
        }
        if(!voivodship.isEmpty() && city.isEmpty()){
            query += " and voivodship like ('" + voivodship + "')";
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
                " order by O.OFFER_ID desc";

        return getOffersByQuery(q);
    }

    private static String generateStatusChange(int offerID, int s)
    {
        return ("UPDATE OFFERS SET OFFER_STATUS = " + s  + " WHERE OFFER_ID = " + offerID);
    }

    public static void addOfferToDatabase(Offer offer) throws SQLException {
        Connecting DB = new Connecting();
        System.out.println(generateInsert(offer));
        DB.alterTable(generateInsert(offer));
        DB.close();
    }


    public static void setOfferStatus(int offerID, int status) throws SQLException {
        Connecting DB = new Connecting();
        DB.alterTable(generateStatusChange(offerID, status));
        DB.close();
    }

    private static ObservableList<String> getCities(String voivodship) throws SQLException {
        Connecting DB = new Connecting();
        Connection conn = DB.getConn();
        ObservableList<String> cities = FXCollections.observableArrayList();
        if (conn != null) {
            Statement stmt;
            stmt = conn.createStatement();
            try {
                ResultSet rs = stmt.executeQuery("SELECT c.NAME from CITIES c join VOIVODSHIPS v using(voivodship_id) WHERE v.name like('" + voivodship + "') order by c.name asc");
                while (rs.next()) {
                    cities.add(rs.getString("name"));
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
        return cities;
    }

    private static ObservableList<String> citiesList = null;

    public static ObservableList<String> getCitiesList(String Voivodship) throws SQLException {
        citiesList = getCities(Voivodship);
        return citiesList;
    }

    private static ObservableList<String> getVoivodships() throws SQLException {
        Connecting DB = new Connecting();
        Connection conn = DB.getConn();
        ObservableList<String> voivodships = FXCollections.observableArrayList();
        if (conn != null) {
            Statement stmt;
            stmt = conn.createStatement();
            try {
                ResultSet rs = stmt.executeQuery("Select NAME from VOIVODSHIPS");
                while (rs.next()) {
                    voivodships.add(rs.getString("name"));
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
        return voivodships;
    }

    private static ObservableList<String> voivodshipsList = null;

    public static ObservableList<String> getVoivodshipsList() throws SQLException {
        voivodshipsList = getVoivodships();
        return voivodshipsList;
    }

    private static String generateUpdate(Offer offer)
    {
        return ("UPDATE OFFERS SET NAME = '" + offer.getItemName() +"', DESCRIPTION = '" +
                offer.getItemDescription() + "', category = '" + offer.getItemCategory() + "' " +
                ", price = " + offer.getPrice() +
                " WHERE OFFER_ID = " + offer.getOfferId());
    }

    public static void updateOffer(Offer offer) throws SQLException {
        Connecting DB = new Connecting();
        DB.alterTable(generateUpdate(offer));
        DB.close();

    }
}

