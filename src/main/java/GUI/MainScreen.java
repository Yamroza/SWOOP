package GUI;

import Classes.Offer;
import Database.Categories;
import Database.Offers;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;


public class MainScreen {

    @FXML
    ComboBox<String> category;

    @FXML
    ListView<Offer> offerList;

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
    private void MyAccountClicked(ActionEvent e) throws IOException{
        App.setRoot("accountScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void initialize() throws SQLException {
        ObservableList<String> categories = Categories.getCategories();
        categories.add(0, "-");
        category.setItems(categories);
        category.getSelectionModel().selectFirst();
        offerList.setItems(Offers.getNextTenOffers());
        offerList.setCellFactory(offerListView -> new OfferListElement());
    }

}
