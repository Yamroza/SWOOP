package GUI;

import Classes.*;

import Database.Categories;
import Database.Imgur;
import Database.Offers;
import Database.Users;
import javafx.fxml.FXML;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
    ComboBox<String> voivodshipDrop;

    @FXML
    ComboBox<String> cityDrop;

    @FXML
    Text errorMessage;

    @FXML
    ImageView imageView;

    final FileChooser fc = new FileChooser();
    String photoLink = "https://i.imgur.com/Moe5dXk.jpg";

    public void AddPhoto() throws Exception {
        fc.setTitle("Wybierz zdjęcie oferty");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files","*.jpg","*.png", "*.bmp" ));
        File chosen_file = fc.showOpenDialog(null);
        if (chosen_file != null) {
            Imgur photo_imgur = new Imgur();
            String link = photo_imgur.putImgurContent(chosen_file);
            this.photoLink = link.replaceAll("\\\\", "/");
            URL url = new URL(this.photoLink);
            File file = new File("work_image.jpg");
            int connectionTimeout = 10 * 1000; // 10 sec
            int readTimeout = 300 * 1000; // 3 min
            FileUtils.copyURLToFile(url, file, connectionTimeout, readTimeout);
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
        }
    }

    @FXML
    private void CancelClicked() throws IOException {
        App.setRoot("mainScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    private String chosen_voivod = "null";

    @FXML
    private void voivodshipDropClicked() throws SQLException {
        this.chosen_voivod = voivodshipDrop.getValue();
        cityDrop.setItems(Offers.getCitiesList(chosen_voivod));
    }

    @FXML
    private void isForSaleClicked()
    {
        price.setDisable(!isForSale.isSelected());
        price.setEditable(isForSale.isSelected());
        price.setText("");
    }

    @FXML
    private void SaveClicked() throws IOException, SQLException {
        User loggedUser = Users.getLoggedUser();
        String name = offerName.getText();
        if(Objects.equals(name, ""))
        {
            errorMessage.setText("Nazwa nie może być pusta");
        }
        else if(!isForExchange.isSelected() && !isForSale.isSelected())
        {
            errorMessage.setText("Wybierz rodzaj oferty");
        }
        else
        {
            float itemPrice = !Objects.equals(price.getText(), "") ? Float.parseFloat(price.getText()) : 0;
            Offer newOffer = new Offer(
                    name,
                    offerDesc.getText(),
                    categoryDrop.getValue(),
                    isForExchange.isSelected(),
                    isForSale.isSelected(),
                    itemPrice,
                    loggedUser.getLogin(),
                    0,
                    cityDrop.getValue(),
                    this.photoLink
            );
            Offers.addOfferToDatabase(newOffer);
            App.setRoot("mainScreen");
            App.myStage.setScene(App.scene);
            App.myStage.sizeToScene();
        }
    }

    @FXML
    private void initialize() throws SQLException, IOException {
        Image image = Imgur.showImageFromLink("https://i.imgur.com/Moe5dXk.jpg");
        imageView.setImage(image);

        categoryDrop.setItems(Categories.getCategoriesList());
        categoryDrop.getSelectionModel().selectFirst();
        voivodshipDrop.setItems(Offers.getVoivodshipsList());
        voivodshipDrop.getSelectionModel().selectFirst();
        cityDrop.setItems(Offers.getCitiesList("dolnośląskie"));
        cityDrop.getSelectionModel().selectFirst();
        price.setEditable(false);
    }
}
