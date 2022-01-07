package GUI;

import Classes.Offer;
import Database.Categories;
import Database.Offers;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import org.controlsfx.control.RangeSlider;
import javafx.fxml.FXML;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.sql.SQLException;


public class MainScreen {

    @FXML
    ListView<Offer> offerList;

    @FXML
    CheckComboBox<String> categories;

    public AnchorPane mainScreenPane;
    public TextField fromTextField;
    public TextField toTextField;


    @FXML
    private void ButtonClicked(ActionEvent e) throws IOException{
        App.setRoot("offerScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void NewOfferClicked(ActionEvent e) throws IOException{
        App.setRoot("newOfferScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void OfferClicked(MouseEvent e) throws IOException{
        Offers.setSelectedOffer(offerList.getSelectionModel().getSelectedItem());
        App.setRoot("offerScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void MyAccountClicked(ActionEvent e) throws IOException{
        App.setRoot("accountScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void initialize() throws SQLException {
        categories.getItems().addAll(Categories.getCategoriesList());
        offerList.setItems(Offers.getNextTenOffers());
        offerList.setCellFactory(offerListView -> new OfferListElement());
    }

}
