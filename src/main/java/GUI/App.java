package GUI;

import Classes.*;
import Database.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import java.io.IOException;

public class App extends Application {

    static Stage myStage;
    static Stage secondStage;
    static Scene scene;
    static Scene secondScene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("loginScreen"));
        myStage = stage;
        myStage.setScene(scene);
        //stage.setResizable(false);
        myStage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/" + fxml + ".fxml"));
            return fxmlLoader.load();
    }

    public static void main(String[] args) throws SQLException{
        Connecting DB = new Connecting();
        DB.alterTable("SELECT * FROM categories");
        DB.close();
        /* System.out.println("Connecting to magical database...");
        Connecting DB = new Connecting("z27", "9wdzsz");
        System.out.println("Closed magical database...");
        DB.close(); */
        launch();
    }

}