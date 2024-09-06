package com.example.drsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Label welcomeLabel;

    public void initialize() {
        // Set a welcome message, optionally personalized
        welcomeLabel.setText("Welcome to the Disaster Response System!");
    }

    // navigate report disaster user interface
    @FXML
    private void reportDisaster() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/drsystem/report-disaster.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Report Disaster");
        stage.setScene(new Scene(root, 620, 440));
        stage.show();
    }

    @FXML
    private void viewDisasters() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/drsystem/view-disasters.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Disaster Table");
        stage.setScene(new Scene(root, 620, 440));
        stage.show();
    }

    public void assessDisaster(ActionEvent event) {
    }

    public void manageNotifications(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/drsystem/login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root, 620, 440)); // Set the desired size for the login window
            stage.setResizable(false); // Make the window size fixed
            stage.show();

            // Close the current dashboard window
            Stage dashboardStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            dashboardStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
