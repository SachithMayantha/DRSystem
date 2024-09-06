package com.example.drsystem.controller;

import com.example.drsystem.DatabaseConnection;
import com.example.drsystem.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;

public class EditUserController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private TextField mobileField;

    private User selectedUser;

    private DatabaseConnection databaseConnection;

    public EditUserController() {
        databaseConnection = new DatabaseConnection();
    }

    public void setUser(User user) {
        this.selectedUser = user;
        nameField.setText(user.getName());
        emailField.setText(user.getEmail());
        roleComboBox.setValue(user.getRole());
        mobileField.setText(user.getMobile());
    }

    @FXML
    private void saveUser() {
        String name = nameField.getText();
        String email = emailField.getText();
        String role = roleComboBox.getValue();
        String mobile = mobileField.getText();

        if (name.isEmpty() || email.isEmpty() || role.isEmpty() || mobile.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill all fields.");
            return;
        }

        String query = "UPDATE user SET name = ?, email = ?, role = ?, mobile = ? WHERE id = ?";

        try (Connection conn = databaseConnection.connect();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, role);
            statement.setString(4, mobile);
            statement.setInt(5, selectedUser.getId());

            statement.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Success", "User updated successfully.");

            // Close the edit window
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to update user.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
