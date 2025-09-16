package com.travelagency.travelagency.controllers;

import com.travelagency.travelagency.models.Reservation;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReservationController {

    @FXML
    private ComboBox<String> filterDestination;
    @FXML
    private DatePicker filterDateDeparture;
    @FXML
    private TextField filterName;
    @FXML
    private TableView<Reservation> clientsTable;
    @FXML
    private TableColumn<Reservation, String> colDepartureDate;
    @FXML
    private TableColumn<Reservation, String> colNom;
    @FXML
    private TableColumn<Reservation, String> colDestination;
    @FXML
    private TableColumn<Reservation, String> colPaiement;
    @FXML
    private TableColumn<Reservation, Integer> colNbPersonnes;
    @FXML
    private TableColumn<Reservation, Void> colActions;

    private final ObservableList<Reservation> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Ajoute des destinations à ton ComboBox
        filterDestination.getItems().addAll(
                "Toliary",
                "Antsohihy",
                "Andapa",
                "Fianarantsoa",
                "Antananarivo",
                "Mahajanga",
                "Toamasina",
                "Antsirabe",
                "Toliara"
        );

        // Optionnel : sélectionner la première destination par défaut
        if (!filterDestination.getItems().isEmpty()) {
            filterDestination.getSelectionModel().selectFirst();
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        colNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        colDestination.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestination()));
        colDepartureDate.setCellValueFactory(cellData -> {
            LocalDateTime departure = cellData.getValue().getDeparture();
            return new SimpleStringProperty(departure != null ? departure.format(formatter) : "");
        });
        colPaiement.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaiement()));
        colNbPersonnes.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNbPersonnes()).asObject());

        // Configuration pour la colonne Actions
        colActions.setMinWidth(160);
        colActions.setMaxWidth(160);
        colActions.setResizable(false);
        colActions.setCellFactory(col -> new TableCell<Reservation, Void>() {
            private final Button btnAccept = new Button("Valider");
            private final Button btnRefuse = new Button("Annuler");
            private final HBox pane = new HBox(5, btnAccept, btnRefuse);

            {
                btnAccept.setOnAction(e -> {
                    Reservation r = getTableView().getItems().get(getIndex());
                    System.out.println("✅ Réservation acceptée pour " + r.getNom());
                });
                btnRefuse.setOnAction(e -> {
                    Reservation r = getTableView().getItems().get(getIndex());
                    System.out.println("❌ Réservation refusée pour " + r.getNom());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : pane);
            }
        });

        // Politique de redimensionnement
        clientsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        System.out.println("Policy: " + clientsTable.getColumnResizePolicy());

        // Ajout des données
        data.addAll(
                new Reservation("Andrianarivony André", "Toliary", LocalDateTime.of(2025, 9, 20, 10, 30), "Payé", 2),
                new Reservation("Ramasitera Felix", "Antsohihy", LocalDateTime.of(2025, 9, 21, 14, 0), "En attente", 4),
                new Reservation("Charlie Mahefa", "Andapa", LocalDateTime.of(2025, 9, 22, 16, 15), "Payé", 1),
                new Reservation("Lala Rabe", "Fianarantsoa", LocalDateTime.of(2025, 10, 1, 9, 0), "Payé", 3),
                new Reservation("Tiana Rasoanaivo", "Antananarivo", LocalDateTime.of(2025, 10, 3, 11, 45), "En attente", 5),
                new Reservation("Mamy Rakoto", "Mahajanga", LocalDateTime.of(2025, 10, 5, 8, 30), "Payé", 2),
                new Reservation("Fara Ravalomanana", "Toamasina", LocalDateTime.of(2025, 10, 7, 13, 15), "En attente", 4),
                new Reservation("Noro Andrianina", "Fianarantsoa", LocalDateTime.of(2025, 10, 10, 15, 0), "Payé", 3),
                new Reservation("Sitraka Rabe", "Antsirabe", LocalDateTime.of(2025, 10, 12, 10, 0), "En attente", 2),
                new Reservation("Hery Rakotomalala", "Toliara", LocalDateTime.of(2025, 10, 15, 14, 30), "Payé", 4)
        );

        clientsTable.setItems(data);
    }




}
