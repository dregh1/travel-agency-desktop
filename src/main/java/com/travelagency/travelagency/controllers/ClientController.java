package com.travelagency.travelagency.controllers;

import com.travelagency.travelagency.models.Client;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;

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

    private final ObservableList<Client> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colNom.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNom()));
        colEmail.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEmail()));
        colTelephone.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTelephone()));
        colFrequence.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getFrequence()).asObject()); // ✅

        // Exemple de données
        data.addAll(
                new Client("Andrianarivony André", "andre@mail.com", "034 12 345 67", 5),
                new Client("Ramasitera Felix", "felix@mail.com", "033 11 222 33", 15),
                new Client("Charlie Mahefa", "charlie@mail.com", "032 99 888 77",12)
        );

        clientsTable.setItems(data);
    }
}
