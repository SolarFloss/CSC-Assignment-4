package application;

import controllers.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
    private String key = "AIzaSyBdffLuBloabj5LhoboDNWqP-vXQL6Pwno";
    private String url1 = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private String address = "12868 Cara Drive";
    private static Stage stage;
    private static Scene scene;
    private static Restaurants restaurants;

    public static Restaurants getData(){return restaurants;}
    public static Stage getStage(){return stage;}
    public static Scene getScene(){return scene;}

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        stage.setTitle("Hello World");
        scene = new Scene(root,415,400);
        stage.show();
        stage.setScene(scene);
        //new Graph();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    restaurants = new Restaurants();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> new MainController());
            }
        }).start();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
