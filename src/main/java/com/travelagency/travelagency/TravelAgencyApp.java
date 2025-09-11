package com.travelagency.travelagency;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TravelAgencyApp  extends Application {

    @Override
    public void start(Stage stage) {
        // ----- BARRE DE MENU -----
        Button homeBtn = new Button("Accueil");
        Button destinationsBtn = new Button("Destinations");
        Button contactBtn = new Button("Contact");

        for (Button btn : new Button[]{homeBtn, destinationsBtn, contactBtn}) {
            btn.setStyle("-fx-background-color: transparent; -fx-font-size: 14px; -fx-text-fill: white;");
            btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #ffffff33; -fx-font-size: 14px; -fx-text-fill: white;"));
            btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: transparent; -fx-font-size: 14px; -fx-text-fill: white;"));
        }

        ToolBar toolBar = new ToolBar(homeBtn, destinationsBtn, contactBtn);
        toolBar.setStyle("-fx-background-color: #008CBA;"); // Bleu moderne

        // ----- SECTION TITRE -----
        Label title = new Label("🌴 SunTravel Agency");
        title.setStyle("-fx-font-size: 34px; -fx-font-weight: bold; -fx-text-fill: #008CBA;");

        Label subtitle = new Label("Explorez le monde avec nous !");
        subtitle.setStyle("-fx-font-size: 18px; -fx-text-fill: #555;");

        VBox headerBox = new VBox(8, title, subtitle);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(20));

        // ----- IMAGE D'ACCUEIL -----
        ImageView banner = new ImageView(new Image(
                getClass().getResource("/com/travelagency/travelagency/01globaltravel.png").toExternalForm(),
                700, 350, true, true));
        banner.setPreserveRatio(true);
        banner.setSmooth(true);
        banner.setStyle("-fx-effect: dropshadow(gaussian, gray, 10, 0.5, 0, 0);");

        // ----- CONTENU PRINCIPAL -----
        Label description = new Label(
                "✈️ Découvrez nos offres exclusives :\n\n" +
                        "🏖 Séjours balnéaires\n" +
                        "🏛 Circuits culturels\n" +
                        "🌍 Aventures nature\n" +
                        "✨ Voyages de luxe\n\n" +
                        "Avec SunTravel, vos rêves deviennent réalité !");
        description.setStyle("-fx-font-size: 15px; -fx-text-fill: #333;");
        description.setWrapText(true);

        Button exploreBtn = new Button("🌐 Explorer nos offres");
        exploreBtn.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20 10 20; -fx-background-radius: 20;");
        exploreBtn.setOnMouseEntered(e -> exploreBtn.setStyle("-fx-background-color: #006F98; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20 10 20; -fx-background-radius: 20;"));
        exploreBtn.setOnMouseExited(e -> exploreBtn.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20 10 20; -fx-background-radius: 20;"));

        VBox contentBox = new VBox(20, banner, description, exploreBtn);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(25));

        // ----- PIED DE PAGE -----
        Label footer = new Label("© 2025 SunTravel Agency - Tous droits réservés");
        footer.setStyle("-fx-font-size: 12px; -fx-text-fill: #555;");
        HBox footerBox = new HBox(footer);
        footerBox.setAlignment(Pos.CENTER);
        footerBox.setPadding(new Insets(15));
        footerBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        // ----- LAYOUT GLOBAL -----
        BorderPane root = new BorderPane();
        root.setTop(toolBar);
        root.setCenter(new VBox(headerBox, contentBox));
        root.setBottom(footerBox);

        Scene scene = new Scene(root, 900, 650);
        stage.setTitle("SunTravel - Agence de Voyage");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
