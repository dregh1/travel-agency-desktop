package com.travelagency.travelagency.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TemplatingController {
    @FXML
    private Button btnChooseImage;

    @FXML
    private ImageView previewImage;

    @FXML
    private TextField descriptionField;

    @FXML
    private void handleChoosePhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")
        );

        // Récupérer la fenêtre actuelle
        Window window = previewImage.getScene().getWindow();

        File selectedFile = fileChooser.showOpenDialog(window); // passer le stage
        if (selectedFile != null) {
            try (FileInputStream fis = new FileInputStream(selectedFile)) {
                Image img = new Image(fis);
                previewImage.setImage(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleSave() {
        String desc = descriptionField.getText();
        if (desc.length() > 100) desc = desc.substring(0, 100);
        System.out.println("Photo enregistrée avec description : " + desc);
        // Ici tu peux ajouter la logique pour sauvegarder la photo et la description
    }
}

