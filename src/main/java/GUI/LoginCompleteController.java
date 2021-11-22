package GUI;

import Classes.User;
import Database.Users;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class LoginCompleteController {

    @FXML
    private Text loginMsg;

    @FXML
    private void initialize() {
        User loggedUser = Users.getLoggedUser();
        String username = loggedUser.getUsername();
        String dateOfAccountCreation = loggedUser.getAccountCreationTime().format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.forLanguageTag("pl"))
        );
        loginMsg.setText("Hi " + username + "!\n"
                + "You are registered since " + dateOfAccountCreation + ".");
    }

}
