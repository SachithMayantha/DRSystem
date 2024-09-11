package com.example.drsystem.controller;

import com.example.drsystem.DatabaseConnection;
import com.example.drsystem.model.Disaster;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisasterTableController {

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
    private TableColumn<Disaster, Integer> priorityNoColumn;

    @FXML
    private TextArea requestedResourcesArea;

    public static int selectedDisasterId; // Store the selected disaster ID

    private List<Disaster> disasterList = new ArrayList<>();

    private DatabaseConnection databaseConnection;

    public DisasterTableController() {
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
        priorityNoColumn.setCellValueFactory(new PropertyValueFactory<>("priorityNo"));

        // Load data from the database
        loadDataFromDatabase();

        // Set the items for the TableView
        disasterTable.setItems(FXCollections.observableArrayList(disasterList));

        // Add listener to display requested resources when a disaster is selected
        disasterTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->
                    showDisasterDetails(newValue));
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
                int priorityNo = resultSet.getInt("priorityNo");
                System.out.println(priorityNo+ "Value");

                // Create a new Disaster object and add it to the list
                Disaster disaster = new Disaster(disasterId, type, location, locationType, description, severity, date.toLocalDate(),reportedBy, priorityNo);
                disasterList.add(disaster);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void showDisasterDetails(Disaster disaster) {
        if (disaster != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/drsystem/disaster-details.fxml"));
                Parent root = loader.load();

                // Get the controller of the details window and pass the selected disaster
                DisasterManageController controller = loader.getController();
                controller.setDisasterDetails(disaster);

                // Open the details window
                Stage stage = new Stage();
                stage.setTitle("Disaster Details");
                stage.setScene(new Scene(root, 620, 440));
                stage.show();

                // Get the resource allocation controller for the resource allocation
                FXMLLoader resourceLoader = new FXMLLoader(getClass().
                        getResource("/com/example/drsystem/health_resource_allocation.fxml"));
                Parent resourceRoot = resourceLoader.load();

                // Get the selected disaster ID
                selectedDisasterId = disaster.getDisasterId();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
