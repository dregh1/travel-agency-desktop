package com.travelagency.travelagency.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class TemplatingController {

    @FXML
    private ImageView photoPreview;

    @FXML
    private TextField descriptionField;

    @FXML
    private void handleChoosePhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Image img = new Image(file.toURI().toString());
            photoPreview.setImage(img);
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

