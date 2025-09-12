package com.travelagency.travelagency.controllers;

import com.travelagency.travelagency.TravelAgencyApp;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HomeController {

    @FXML
    private ImageView banner;

    @FXML
    public void initialize() {
        banner.setImage(new Image(
                getClass().getResource("/com/travelagency/travelagency/01globaltravel.png").toExternalForm()
        ));
    }

    @FXML
    private void handleLogout() {
        try {
            TravelAgencyApp.showLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}