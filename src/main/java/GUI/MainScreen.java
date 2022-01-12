package GUI;

import Classes.Offer;
import Database.Categories;
import Database.Offers;
import Database.Users;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import org.controlsfx.control.RangeSlider;
import javafx.fxml.FXML;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;


public class MainScreen {

    private static final DecimalFormat df = new DecimalFormat("0");

    private NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);

    @FXML
    RangeSlider range;

    @FXML
    ListView<Offer> offerList;

    @FXML
    CheckComboBox<String> categories;

    @FXML
    TextField searchTextField;

    @FXML
    ToggleGroup sort;

    @FXML
    CheckBox isExchangeButton;

    @FXML
    CheckBox isSaleButton;

    @FXML
    ComboBox<String> voivodshipDrop;

    @FXML
    ComboBox<String> cityDrop;

    public AnchorPane mainScreenPane;
    public TextField fromTextField;
    public TextField toTextField;


    @FXML
    private void searchEnter(KeyEvent e) throws IOException, SQLException{
        if(e.getCode() == KeyCode.ENTER) {
            // wyszukiwanie
        }
    }

    @FXML
    private void rangeEnter(KeyEvent e) throws IOException, SQLException{
        if(e.getCode() == KeyCode.ENTER) {
            // wyszukiwanie
        }
    }

    @FXML
    private void NewOfferClicked(ActionEvent e) throws IOException{
        App.setRoot("newOfferScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void OfferClicked(MouseEvent e) throws IOException{
        Offer selectedOffer = offerList.getSelectionModel().getSelectedItem();
        if(selectedOffer != null)
        {
            Offers.setSelectedOffer(selectedOffer);
            App.setRoot("offerScreen");
            App.myStage.setScene(App.scene);
            App.myStage.sizeToScene();
        }
    }

    private String chosen_voivod = "null";

    @FXML
    private void voivodshipDropClicked() throws SQLException {
        this.chosen_voivod = voivodshipDrop.getValue();
        cityDrop.setItems(Offers.getCitiesList(chosen_voivod));
    }

    @FXML
    private void MyAccountClicked(ActionEvent e) throws IOException{
        App.setRoot("accountScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void ViewListWithConditions() throws SQLException {
        // name
        String name =  searchTextField.getText();

        //category
        ObservableList <String> SelectedCategories = categories.getCheckModel().getCheckedItems();

        // sorting
        String sorting = "";
        RadioButton rb = (RadioButton)sort.getSelectedToggle();
        if(rb != null){
            sorting = rb.getText();
        }

        // is_exchange
        int is_exchange = 0;
        if(isExchangeButton.isSelected()) {
            is_exchange = 1;
        }

        // is_for_sell
        int is_for_sale = 0;
        if(isSaleButton.isSelected()) {
            is_for_sale = 1;
        }

        // price range
        int price_from = -1;
        try {
            String t = fromTextField.getText();
            price_from = Integer.parseInt(t);
        }
        catch (NumberFormatException ignored){}

        int price_to = -1;
        try {
            String t = toTextField.getText();
            price_to = Integer.parseInt(t);
        }
        catch (NumberFormatException ignored){}

        String city = cityDrop.getValue();
        if (city == null) { city = "" ;}


        offerList.setItems(null);
        ObservableList<Offer> offers = Offers.getOffersByCond(name, SelectedCategories, is_exchange, is_for_sale,
                price_from, price_to, city, sorting);
        offers.removeIf(offer1 -> Objects.equals(offer1.getSeller(), Users.getLoggedUser().getLogin()));
        offerList.setItems(offers);
    }

    @FXML
    private void initialize() throws SQLException, ParseException {
        categories.getItems().addAll(Categories.getCategoriesList());
        ObservableList<Offer> offers = Offers.getNextTenOffers();
        offers.removeIf(offer1 -> Objects.equals(offer1.getSeller(), Users.getLoggedUser().getLogin()));
        offerList.setItems(offers);
        offerList.setCellFactory(offerListView -> new OfferListElement());
        voivodshipDrop.setItems(Offers.getVoivodshipsList());
        Float maxPrice = Collections.max(offers, (o1, o2) -> {
            if(o1.getPrice() != null && o2.getPrice() != null) {
                return Float.compare(o1.getPrice(), o2.getPrice());
            }
            else if (o1.getPrice() == null){
                return Float.compare(0 , o2.getPrice());
            }
            else if (o2.getPrice() == null){
                return Float.compare(o1.getPrice(), 0);
            }
            if(o1.getPrice() == null && o2.getPrice() == null) {
                return 0;
            }
            return 0;
        }).getPrice();
        toTextField.setText(String.valueOf(maxPrice));
        range.setMax(maxPrice);
        range.setHighValue(maxPrice);
        range.setMajorTickUnit(maxPrice / 10);

        rangeListen();

    }

    @FXML
    private void rangeListen() {

            range.lowValueProperty().addListener((observable, oldValue, newValue) -> {
                String low = String.valueOf(df.format(range.getLowValue()));
                fromTextField.setText(low);
            });

            range.highValueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    String high = String.valueOf(df.format(range.getHighValue()));
                    toTextField.setText(high);
                }
            });

        fromTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            Number number = null;
            try {
                number = format.parse(newValue);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            double d = number.doubleValue();

            range.setLowValue(d);
        });

        toTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            Number number = null;
            try {
                number = format.parse(newValue);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            double d = number.doubleValue();

            range.setHighValue(d);
        });

    }
}
