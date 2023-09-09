package com.flight.flight_system.Data;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

import me.friwi.tello4j.api.drone.TelloDrone;
import me.friwi.tello4j.api.exception.*;
import me.friwi.tello4j.wifi.impl.WifiDrone;

public class FlightOperations {

    /**
     * Attributes
     */
    TelloDrone drone = new WifiDrone();

    /**
     * Autonomous flight method
     * This will most likely use a machine model to predict a flight plan
     */
    public void startMission() {
        if (showConfirmationDialog("Mission Start", "Are you sure you want to start the mission?")) {
        }
    }

    /**
     * Starts the drone flight
     */
    public void takeoff() {
        if (showConfirmationDialog("Takeoff", "Are you sure you want to takeoff?")) {
            try {
                drone.takeoff();
            } catch (TelloNetworkException | TelloCommandTimedOutException | TelloCustomCommandException | TelloGeneralCommandException e) {
                e.printStackTrace();
            }
        }
    }

    public void land() {
        if (showConfirmationDialog("Land", "Are you sure you want to land?")) {
            try {
                drone.land();
            } catch (TelloNetworkException | TelloCommandTimedOutException | TelloCustomCommandException | TelloGeneralCommandException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnLeft() {
        try {
            drone.turnLeft(2);
        } catch (TelloNetworkException | TelloCommandTimedOutException | TelloCustomCommandException |
                 TelloGeneralCommandException | TelloNoValidIMUException e) {
            e.printStackTrace();
        }
    }

    public void turnRight() {
        try {
            drone.turnRight(2);
        } catch (TelloNetworkException | TelloCommandTimedOutException | TelloCustomCommandException |
                 TelloGeneralCommandException | TelloNoValidIMUException e) {
            e.printStackTrace();
        }
    }

    public void moveForward() {
        try {
            drone.forward(2);
        } catch (TelloNetworkException | TelloCommandTimedOutException | TelloCustomCommandException |
                 TelloGeneralCommandException | TelloNoValidIMUException e) {
            e.printStackTrace();
        }
    }

    public void moveBackward() {
        try {
            drone.backward(2);
        } catch (TelloNetworkException | TelloCommandTimedOutException | TelloCustomCommandException |
                 TelloGeneralCommandException | TelloNoValidIMUException e) {
            e.printStackTrace();
        }
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

    /**
     *
     * Dialog for drone functions
     *
     * @param title title of dialog
     * @param message message of dialog
     * @return true if OK selected
     */
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
