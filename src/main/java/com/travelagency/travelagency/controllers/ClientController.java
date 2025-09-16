package com.travelagency.travelagency.controllers;

import com.travelagency.travelagency.models.Client;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.beans.property.SimpleStringProperty;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ClientController {

    @FXML
    private TableView<Client> clientsTable;

    @FXML
    private TableColumn<Client, String> colNom;
    @FXML
    private TableColumn<Client, String> colEmail;
    @FXML
    private TableColumn<Client, String> colTelephone;
    @FXML
    private TableColumn<Client, Integer> colFrequence;

    @FXML
    private TextField clientNomField;
    @FXML
    private TextField clientEmailField;
    @FXML
    private TextField clientTelephoneField;

    @FXML
    private GridPane frequenceGrid;

    private final ObservableList<Client> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colNom.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNom()));
        colEmail.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEmail()));
        colTelephone.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTelephone()));
        colFrequence.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getFrequence()).asObject());

        data.addAll(
                new Client("Andrianarivony André", "andre.a@mail.com", "034 12 345 67", 5),
                new Client("Ramasitera Felix", "felix.r@mail.com", "033 11 222 33", 15),
                new Client("Charlie Mahefa", "charlie.m@mail.com", "032 99 888 77", 12),
                new Client("Randria Lova", "lova.r@mail.com", "034 56 789 01", 8),
                new Client("Rakoto Sarah", "sarah.r@mail.com", "033 44 555 66", 2),
                new Client("Rajao Tahina", "tahina.j@mail.com", "032 77 666 55", 20),
                new Client("Bako Fara", "fara.b@mail.com", "034 98 765 43", 1),
                new Client("Herilanto Paul", "paul.h@mail.com", "033 21 345 67", 10),
                new Client("Johanne Ralisa", "johanne.r@mail.com", "034 10 987 65", 7),
                new Client("Sanda Tolotra", "sanda.t@mail.com", "033 98 765 43", 18),
                new Client("Voahangy Solofo", "voahangy.s@mail.com", "032 87 654 32", 3),
                new Client("Tafita Rija", "tafita.r@mail.com", "034 45 678 90", 25),
                new Client("Mialy Tantely", "mialy.t@mail.com", "033 67 890 12", 6),
                new Client("Fenosoa Lanto", "fenosoa.l@mail.com", "032 54 321 09", 14),
                new Client("Kanto Hery", "kanto.h@mail.com", "034 78 901 23", 9),
                new Client("Harisoa Rivo", "harisoa.r@mail.com", "033 89 012 34", 17),
                new Client("Lina Farida", "lina.f@mail.com", "032 12 345 67", 4),
                new Client("Tojo Solo", "tojo.s@mail.com", "034 23 456 78", 11)
        );
        clientsTable.setItems(data);

        clientsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                clientNomField.setText(newSelection.getNom());
                clientEmailField.setText(newSelection.getEmail());
                clientTelephoneField.setText(newSelection.getTelephone());
                updateFrequenceGrid(newSelection.getFrequence());
            } else {
                clientNomField.setText("");
                clientEmailField.setText("");
                clientTelephoneField.setText("");
                frequenceGrid.getChildren().clear();
            }
        });
    }

    /**
     * Met à jour la grille de fréquence avec une distribution aléatoire.
     * @param frequence Le nombre de réservations du client.
     */
    private void updateFrequenceGrid(int frequence) {
        frequenceGrid.getChildren().clear();
        int maxDays = 30;
        int squareSize = 15;

        Color color1 = Color.web("#ebedf0"); // Pas de réservation
        Color color2 = Color.web("#c6e48b"); // 1-4
        Color color3 = Color.web("#7bc96f"); // 5-9
        Color color4 = Color.web("#239a3b"); // 10-14
        Color color5 = Color.web("#146124"); // 15+

        // Liste des jours pour colorier
        Set<Integer> reservationDays = new HashSet<>();
        Random random = new Random();

        // Sélectionne aléatoirement 'frequence' jours sans doublons
        while (reservationDays.size() < frequence) {
            reservationDays.add(random.nextInt(maxDays));
        }

        // Boucle pour dessiner tous les jours
        for (int i = 0; i < maxDays; i++) {
            Rectangle rect = new Rectangle(squareSize, squareSize);

            // Si ce jour fait partie des jours de réservation
            if (reservationDays.contains(i)) {
                if (i < 4) rect.setFill(color2);
                else if (i < 9) rect.setFill(color3);
                else if (i < 14) rect.setFill(color4);
                else rect.setFill(color5);
            } else {
                rect.setFill(color1); // Jour sans réservation
            }

            frequenceGrid.add(rect, i / 7, i % 7);
        }
    }
}