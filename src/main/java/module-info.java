module com.travelagency.travelagency {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    requires org.kordamp.bootstrapfx.core;

    opens com.travelagency.travelagency to javafx.fxml;
    exports com.travelagency.travelagency;

    // 🔑 Ouvrir les controllers à FXMLLoader
    opens com.travelagency.travelagency.controllers to javafx.fxml;
    exports com.travelagency.travelagency.controllers;
}