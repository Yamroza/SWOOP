package GUI;

import Database.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class LoginScreen {

    @FXML
    TextField loginInput;

    @FXML
    PasswordField passwordInput;

    @FXML
    Text errorMessage;

    public void loadMain() throws IOException
    {
        App.setRoot("mainScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
        App.myStage.setX(320);
        App.myStage.setY(50);
    }

    @FXML
    private void LoginClicked() throws IOException, SQLException {
        String login = loginInput.getText();
        String password = passwordInput.getText();
        if (Users.loginCheck(login, password))
        {
            loadMain();
        }
        errorMessage.setText("Login or password is incorrect.");
    }

    @FXML
    private void RegisterClicked() throws IOException {
        App.setRoot("registerScreen");
        App.myStage.setScene(App.scene);
        App.myStage.sizeToScene();
    }

    private void loadOffers(int num)
    {
        int x = 0;
        int y = 0;

        VBox offers = new VBox();

        for(int i=0; i<num; i++)
        {
            AnchorPane a = new AnchorPane();
            Button b = new Button("Button");
            //b.setOnAction(loadMain());
            a.getChildren().addAll(b);
            offers.getChildren().add(a);
        }
    }
}

