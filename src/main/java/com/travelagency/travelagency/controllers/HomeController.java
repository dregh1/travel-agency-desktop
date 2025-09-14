package com.travelagency.travelagency.controllers;

import com.travelagency.travelagency.TravelAgencyApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;

public class HomeController {

    @FXML
    private TreeView<String> sidebarTree;

    @FXML
    private Button logoutButton;

    @FXML
    private BorderPane rootPane;

    @FXML
    public void initialize() {
        // ===== Sidebar TreeView =====
        TreeItem<String> root = new TreeItem<>("Root");
        root.setExpanded(true);

        TreeItem<String> gestionBackOffice = new TreeItem<>("Gestion interne & Back-office");
        gestionBackOffice.getChildren().addAll(
                new TreeItem<>("Gestion de stock"),
                new TreeItem<>("Comptabilité"),
                new TreeItem<>("Templating")
        );

        TreeItem<String> support = new TreeItem<>("Support & Service client interne");

        root.getChildren().addAll(gestionBackOffice, support);

        sidebarTree.setRoot(root);
        sidebarTree.setShowRoot(false);

        // ===== Style hover pour TreeView =====
        sidebarTree.setCellFactory(tv -> new TreeCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    setStyle("-fx-padding: 5 10 5 10; -fx-font-size: 14px;");
                    setOnMouseEntered(e -> setStyle("-fx-background-color: #e0f0ff; -fx-padding: 5 10 5 10;"));
                    setOnMouseExited(e -> setStyle("-fx-padding: 5 10 5 10;"));
                }
            }
        });

        // ===== TreeView Click Listener =====
        sidebarTree.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                String value = newVal.getValue();
                System.out.println("Menu sélectionné: " + value);

                switch (value) {
                    case "Templating":
                        loadCenterContent("/com/travelagency/travelagency/views/templating.fxml");
                        break;
                    case "Gestion de stock":
//                        loadCenterContent("/com/travelagency/travelagency/views/stock.fxml");
                        break;
//                    case "Comptabilité":
//                        loadCenterContent("/com/travelagency/travelagency/views/comptabilite.fxml");
//                        break;
                    // ajouter d'autres items si besoin
                }
            }
        });

        // ===== Déconnexion hover =====
        if (logoutButton != null) {
            logoutButton.setOnMouseEntered(e -> logoutButton.setStyle(
                    "-fx-background-color: #c0392b; -fx-text-fill: white; -fx-font-weight: bold; " +
                            "-fx-padding: 10 30; -fx-background-radius: 20;"
            ));
            logoutButton.setOnMouseExited(e -> logoutButton.setStyle(
                    "-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; " +
                            "-fx-padding: 10 30; -fx-background-radius: 20;"
            ));
        }

    }

    @FXML
    private void handleLogout() {
        try {
            TravelAgencyApp.showLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===== méthode pour remplacer le center par une autre vue =====
    private void loadCenterContent(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node content = loader.load();
            rootPane.setCenter(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
