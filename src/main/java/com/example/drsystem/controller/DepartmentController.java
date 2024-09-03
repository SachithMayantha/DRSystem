package com.example.drsystem.controller;

import com.example.drsystem.model.Disaster;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class DepartmentController {

    @FXML
    private TableView<Disaster> disasterTable;

    @FXML
    private TableColumn<Disaster, Integer> disasterIdColumn;

    @FXML
    private TableColumn<Disaster, String> disasterTypeColumn;

    @FXML
    private TableColumn<Disaster, String> locationColumn;

    @FXML
    private TableColumn<Disaster, String> severityColumn;

    @FXML
    private TableColumn<Disaster, String> dateColumn;

    @FXML
    private TextArea requestedResourcesArea;

    private List<Disaster> disasterList;

    public void initialize() {
        // Set up the columns in the table
        disasterIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        disasterTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        severityColumn.setCellValueFactory(new PropertyValueFactory<>("severity"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Load data (this would typically be loaded from a database)
//        disasterList.addAll();

        // Add listener to display requested resources when a disaster is selected
        disasterTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showRequestedResources(newValue)
        );
    }

    private void showRequestedResources(Disaster disaster) {
        if (disaster != null) {
            // Fetch the requested resources for the selected disaster from the database
            // Example: display hardcoded data for now
            requestedResourcesArea.setText("Requested Resources:\n\n- 10 Firefighters\n- 2 Firetrucks\n- Medical Supplies");
        } else {
            requestedResourcesArea.clear();
        }
    }

    @FXML
    private void respondToRequest(ActionEvent event) {
        // Implement the logic to respond to the resource request
        // Example: Show a confirmation message or navigate to a response form
//        System.out.println("Responding to request for disaster ID: " + disasterTable.getSelectionModel().getSelectedItem().getId());
    }
}
