package com.travelagency.travelagency.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class TemplatingController {
    @FXML
    private TextField destinationField;
    @FXML
    private TextField amountField;
    @FXML
    private TilePane templatesContainer;

    @FXML
    private Button btnChooseImage;

    @FXML
    private ImageView previewImage;

    @FXML
    private TextField descriptionField;

    // Variable de classe (utilisée dans les 2 méthodes)
    private File selectedFile;

    private static final String TEMPLATE_DIR = "templates";
    private static final String TEMPLATE_JSON = "templates.json";

    @FXML
    public void initialize() {
        loadTemplates(); // Charger les templates existants au démarrage
    }

    @FXML
    private void handleChoosePhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")
        );

        // Récupérer la fenêtre actuelle
        Window window = previewImage.getScene().getWindow();

        // ⚠️ Ici on enlève la redéclaration `File selectedFile = ...`
        selectedFile = fileChooser.showOpenDialog(window);

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
        if (selectedFile == null) {
            System.out.println("⚠️ Aucune photo sélectionnée !");
            return;
        }

        String desc = descriptionField.getText();
        if (desc.length() > 100) desc = desc.substring(0, 100);

        try {
            // Créer le dossier templates si inexistant
            File dir = new File(TEMPLATE_DIR);
            if (!dir.exists()) dir.mkdirs();

            // Nom unique pour éviter les collisions
            String uniqueName = UUID.randomUUID().toString() + "-" + selectedFile.getName();
            File destFile = new File(dir, uniqueName);

            // Copier l'image
            Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Charger le JSON existant ou créer une liste vide
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, String>> templates = new ArrayList<>();

            File jsonFile = new File(TEMPLATE_JSON);
            if (jsonFile.exists()) {
                templates = mapper.readValue(jsonFile, new TypeReference<List<Map<String, String>>>(){});
            }

            // Ajouter le nouvel enregistrement
            Map<String, String> entry = new HashMap<>();
            entry.put("file", destFile.getPath());
            entry.put("description", desc);

            String amount = amountField.getText(); // ton TextField pour le montant
            entry.put("amount", amount);
            templates.add(entry);

            // Sauvegarder le JSON
            mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, templates);

            System.out.println("✅ Photo enregistrée : " + destFile.getPath() + " avec description : " + desc);

            // reset UI
            descriptionField.clear();
            previewImage.setImage(null);
            selectedFile = null;
            loadTemplates();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTemplates() {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File(TEMPLATE_JSON);
        templatesContainer.getChildren().clear();

        if (jsonFile.exists() && jsonFile.length() > 0) {
            try {
                List<Map<String, String>> templates = mapper.readValue(
                        jsonFile,
                        new TypeReference<List<Map<String, String>>>() {}
                );

                for (Map<String, String> entry : templates) {
                    String path = entry.get("file");
                    String desc = entry.get("description");
                    String amount = entry.getOrDefault("amount", "0"); // récupère amount

                    ImageView iv = new ImageView(new Image(new File(path).toURI().toString()));
                    iv.setFitWidth(150);
                    iv.setPreserveRatio(true);

                    Label labelDesc = new Label(desc);
                    labelDesc.setWrapText(true);
                    labelDesc.setMaxWidth(150);

                    Label labelAmount = new Label("Montant : " + amount);
                    labelAmount.setWrapText(true);
                    labelAmount.setMaxWidth(150);
                    labelAmount.setStyle("-fx-font-weight: normal; -fx-text-fill: green;");

                    VBox itemBox = new VBox(iv, labelDesc, labelAmount);
                    itemBox.setSpacing(5);
                    itemBox.setStyle("-fx-border-color: #ddd; -fx-padding: 5; -fx-background-color: #f9f9f9;");

                    templatesContainer.getChildren().add(itemBox);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
