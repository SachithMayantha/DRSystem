package com.example.drsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label welcomeLabel;

    public void initialize() {
        // Set a welcome message, optionally personalized
        welcomeLabel.setText("Welcome to the Disaster Response System!");
    }

    @FXML
    private void reportDisaster() {
        // Logic to navigate to the "Report Disaster" screen
    }

    @FXML
    private void viewDisasters() {
        // Logic to navigate to the "View Disasters" screen
    }

}
