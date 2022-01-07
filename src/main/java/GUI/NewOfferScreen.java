package GUI;

import Classes.*;

import Database.Categories;
import Database.Offers;
import Database.Users;
import javafx.fxml.FXML;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class NewOfferScreen {

    @FXML
    TextField offerName;

    @FXML
    TextArea offerDesc;

    @FXML
    CheckBox isForExchange;

    @FXML
    CheckBox isForSale;

    @FXML
    TextField price;

    @FXML
    ComboBox<String> categoryDrop;

    @FXML
    Text errorMessage;

    @FXML
    private void CancelClicked() throws IOException {
        App.setRoot("mainScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    @FXML
    private void isForSaleClicked()
    {
        price.setEditable(isForSale.isSelected());
        price.setText("");
    }

    @FXML
    private void SaveClicked() throws IOException, SQLException {
        User loggedUser = Users.getLoggedUser();
        String name = offerName.getText();
        if(Objects.equals(name, ""))
        {
            errorMessage.setText("Name can't be empty");
        }
        else
        {
            float itemPrice = Float.parseFloat(price.getText());
            Offer newOffer = new Offer(
                    Offers.getNewOfferId(),
                    name,
                    offerDesc.getText(),
                    categoryDrop.getValue(),
                    isForExchange.isSelected(),
                    isForSale.isSelected(),
                    itemPrice,
                    loggedUser.getLogin(),
                    0
            );
            Offers.addOfferToDatabase(newOffer);
            App.setRoot("mainScreen");
            App.myStage.setScene(App.scene);
            App.myStage.sizeToScene();
        }
    }

    @FXML
    private void initialize() throws SQLException {
        categoryDrop.setItems(Categories.getCategoriesList());
        categoryDrop.getSelectionModel().selectFirst();
    }
}
