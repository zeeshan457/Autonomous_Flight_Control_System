package com.flight.flight_system.Data;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

public class FlightOperations {

    public void startMission() {
        if (showConfirmationDialog("Mission Start", "Are you sure you want to start the mission?")) {
        }
    }

    public void takeoff() {
        if (showConfirmationDialog("Takeoff", "Are you sure you want to takeoff?")) {
        }
    }

    public void land() {
        if (showConfirmationDialog("Land", "Are you sure you want to land?")) {
        }
    }

    public void turnLeft() {
        // Implement the logic to initiate the autonomous mission
        // Call your autonomous flight control code here
    }

    public void turnRight() {
        // Implement the logic to initiate the autonomous mission
        // Call your autonomous flight control code here
    }

    public void moveForward() {
        // Implement the logic to initiate the autonomous mission
        // Call your autonomous flight control code here
    }

    // Event handler for setting waypoints
    public void setWaypoints() {
//        String waypointsInput = waypointsTextArea.getText();
        // Process the waypointsInput and execute autonomous flight to follow the waypoints
        // Implement this logic according to the drone's SDK or your autonomous flight control system.
        // The waypointsInput will contain the coordinates (latitude, longitude) of the waypoints entered by the user.
        // For example, you can split the input and extract latitude and longitude values.
        // Then, use these values to execute autonomous flight to the defined waypoints.
        // Make sure to comply with the drone's safety guidelines and flight restrictions.
    }

    // New method for the range finder operation
    public void findDroneUsingRangeFinder(TextArea text) {
        // Implement the logic for finding the drone using the range finder
        // For example, you can call methods from the FlightOperations class and update the rangeFinderOutput TextArea accordingly.

        String rangeFinderResult = "Drone found at coordinates: (latitude, longitude)";
        text.setText(rangeFinderResult);
    }

    private boolean showConfirmationDialog(String title, String message) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(title);
        confirmationDialog.setHeaderText(null);
        confirmationDialog.setContentText(message);

        // Set the button types (OK and Cancel)
        confirmationDialog.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

        // Show and wait for the user's response
        ButtonType result = confirmationDialog.showAndWait().orElse(ButtonType.CANCEL);

        // Return true if the user clicked OK, false otherwise
        return result == ButtonType.OK;
    }
}
