package com.travelagency.travelagency.controllers;

import com.travelagency.travelagency.models.Reservation;
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
        // Associer les colonnes aux getters
        colNom.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNom()));
        colDestination.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDestination()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        colDepartureDate.setCellValueFactory(cellData -> {
            LocalDateTime departure = cellData.getValue().getDeparture();
            String formatted = departure != null ? departure.format(formatter) : "";
            return new SimpleStringProperty(formatted);
        });

        // Colonne des actions
        colActions.setCellFactory(col -> new TableCell<>() {
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
        colPaiement.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPaiement()));
        colNbPersonnes.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getNbPersonnes()).asObject());

        // Colonne des actions (Accepter / Refuser)
        colActions.setCellFactory(col -> new TableCell<>() {
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
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(pane);
                }
            }
        });

        // Exemple de données
        data.addAll(
                new Reservation("Andrianarivony André", "Toliary",LocalDateTime.of(2025, 9, 20, 10, 30), "Payé", 2),
                new Reservation("Ramasitera Felix", "Antsohihy",LocalDateTime.of(2025, 9, 21, 14, 0), "En attente", 4),
                new Reservation("Charlie Mahefa", "Andapa",LocalDateTime.of(2025, 9, 22, 16, 15), "Payé", 1)
        );

        clientsTable.setItems(data);
    }
}
