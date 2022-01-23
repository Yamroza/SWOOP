package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    static Stage myStage;
    static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("loginScreen"));
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/app.css")).toExternalForm());
        myStage = stage;
        myStage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("/swoop_logo.png"))));
        myStage.setTitle("Swoop");
        myStage.setScene(scene);
        myStage.setResizable(false);
        myStage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/" + fxml + ".fxml"));
            return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}