package com.travelagency.travelagency.controllers;
import com.travelagency.travelagency.TravelAgencyApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleLogin() {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        if ("admin".equals(user) && "1234".equals(pass)) {
            try {
                TravelAgencyApp.showHome();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            messageLabel.setText("❌ Identifiant ou mot de passe incorrect !");
        }
    }
}
