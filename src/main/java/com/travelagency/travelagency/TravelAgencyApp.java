package com.travelagency.travelagency;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TravelAgencyApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        showLogin();
    }

    public static void showLogin() throws Exception {
        FXMLLoader loader = new FXMLLoader(TravelAgencyApp.class.getResource("login.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Connexion - SunTravel");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showHome() throws Exception {
        FXMLLoader loader = new FXMLLoader(TravelAgencyApp.class.getResource("home.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("SunTravel - Agence de Voyage");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
