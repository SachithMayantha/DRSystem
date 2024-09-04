package com.example.drsystem.controller;

import com.example.drsystem.DatabaseConnection;
import com.example.drsystem.model.Disaster;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private TableColumn<Disaster, String> locationTypeColumn;

    @FXML
    private TableColumn<Disaster, String> descriptionColumn;

    @FXML
    private TableColumn<Disaster, String> severityColumn;

    @FXML
    private TableColumn<Disaster, String> dateColumn;

    @FXML
    private TableColumn<Disaster, String> reportedByColumn;

    @FXML
    private TextArea requestedResourcesArea;

    private List<Disaster> disasterList = new ArrayList<>();

    private DatabaseConnection databaseConnection;

    public DepartmentController(){
        databaseConnection = new DatabaseConnection();
    }

    public void initialize() {
        // Set up the columns in the table
        disasterIdColumn.setCellValueFactory(new PropertyValueFactory<>("disasterId"));
        disasterTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        locationTypeColumn.setCellValueFactory(new PropertyValueFactory<>("locationType"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        severityColumn.setCellValueFactory(new PropertyValueFactory<>("severity"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        reportedByColumn.setCellValueFactory(new PropertyValueFactory<>("reportedBy"));

        // Load data from the database
        loadDataFromDatabase();

        // Set the items for the TableView
        disasterTable.setItems(FXCollections.observableArrayList(disasterList));

        // Add listener to display requested resources when a disaster is selected
        disasterTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showRequestedResources(newValue)
        );
    }

    private void loadDataFromDatabase() {
        String query = "SELECT * FROM drs.disaster";

        try (Connection conn = databaseConnection.connect();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int disasterId = resultSet.getInt("disasterId");
                String type = resultSet.getString("type");
                String location = resultSet.getString("location");
                String locationType = resultSet.getString("locationType");
                String description = resultSet.getString("description");
                String severity = resultSet.getString("severity");
                java.sql.Date date = resultSet.getDate("date");
                String reportedBy = resultSet.getString("reportedBy");
                System.out.println("Location Type :"+locationType);
                // Create a new Disaster object and add it to the list
                Disaster disaster = new Disaster(disasterId, type, location, locationType, description, severity, date.toLocalDate(),reportedBy);
                disasterList.add(disaster);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
