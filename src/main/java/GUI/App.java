package GUI;

import Classes.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    static Scene scene;
    static ArrayList<User> users = new ArrayList<>();
    static User loggedUser;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("loginScreen"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/" + fxml + ".fxml"));
            return fxmlLoader.load();
    }

    public static User checkIfUserIsInBase(String givenUsername) {
        for (User user : users) {
            if (Objects.equals(user.getUsername(), givenUsername)) {
                return user;
            }
        }
        return null;
    }

    public static void addUser(String login, String password) {
        users.add(new User(login, password));
    }

    public static void setLoggedUser(User user) {
       loggedUser = user;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void main(String[] args) {
        users.add(new User("admin", "admin"));
        launch();
    }

}