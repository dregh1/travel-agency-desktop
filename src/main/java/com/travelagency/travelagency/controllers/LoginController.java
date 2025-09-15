package com.travelagency.travelagency.controllers;
import com.travelagency.travelagency.TravelAgencyApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private ImageView banner;

    @FXML
    public void initialize() {
        banner.setImage(new Image(
                getClass().getResource("/com/travelagency/travelagency/01globaltravel.png").toExternalForm()
        ));
    }
    @FXML
    private void handleLogin() {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        if (("admin".equals(user) && "1234".equals(pass) ) || 1==1 ) {
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
