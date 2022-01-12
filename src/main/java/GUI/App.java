package GUI;
import Database.Imgur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Collections;

import org.apache.commons.io.FileUtils;

public class App extends Application {

    static Stage myStage;
    //static Stage secondStage;
    static Scene scene;
    //static Scene secondScene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("loginScreen"));
//        String css = this.getClass().getResource("app.css").toExternalForm();
//        scene.getStylesheets().add(css);
        scene.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
        myStage = stage;
        myStage.getIcons().add(new Image(App.class.getResourceAsStream("/pudzian.jpg")));
        myStage.setTitle("Swoop");
        myStage.setScene(scene);
        myStage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/" + fxml + ".fxml"));
            return fxmlLoader.load();
    }


    public static void main(String[] args) throws IOException {
        launch();
    }

}