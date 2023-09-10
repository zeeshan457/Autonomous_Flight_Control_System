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
     * This will be a basic mission using a set of methods for interesting movements
     */
    public void startMission() {
        if (showConfirmationDialog("Mission Start", "Are you sure you want to start the mission?")) {

            // Takeoff
            takeoff();

            // Execute a series of movements
            moveForward();  // Move forward
            turnRight();    // Turn right
            moveForward();  // Move forward
            turnLeft();     // Turn left
            moveBackward(); // Move backward

            // Execute a circular movement pattern
            executeCircle(3, 90); // Circles with a radius of 3 meters and 90-degree segments

            // Land
            land();
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

    /**
     * Method to turn the drone left
     */
    public void turnLeft() {
        try {
            drone.turnLeft(2);
        } catch (TelloNetworkException | TelloCommandTimedOutException | TelloCustomCommandException |
                 TelloGeneralCommandException | TelloNoValidIMUException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to turn the drone right
     */
    public void turnRight() {
        try {
            drone.turnRight(2);
        } catch (TelloNetworkException | TelloCommandTimedOutException | TelloCustomCommandException |
                 TelloGeneralCommandException | TelloNoValidIMUException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to move the drone forward
     */
    public void moveForward() {
        try {
            drone.forward(2);
        } catch (TelloNetworkException | TelloCommandTimedOutException | TelloCustomCommandException |
                 TelloGeneralCommandException | TelloNoValidIMUException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to move the drone backward
     */
    public void moveBackward() {
        try {
            drone.backward(2);
        } catch (TelloNetworkException | TelloCommandTimedOutException | TelloCustomCommandException |
                 TelloGeneralCommandException | TelloNoValidIMUException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes a circular movement pattern with the given radius and segment angle
     *
     * @param radius  The radius of the circle in meters
     * @param degrees The angle for each segment in degrees
     */
    private void executeCircle(double radius, int degrees) {

        // Calculate the circumference of the circle
        double circumference = 2 * Math.PI * radius;
        // Calculate the number of segments needed to complete a 360-degree circle
        int segments = 360 / degrees;

        // Execute the circular movement
        for (int i = 0; i < segments; i++) {
            moveForward(); // Move forward
            turnRight();
        }
    }

    /**
     * Method to set way points
     */
    public void setWaypoints(String waypointsInput) {

        // Split the input into individual waypoints (latitude, longitude pairs)
        String[] waypoints = waypointsInput.split("\n"); // Assuming each waypoint is on a new line

        // Iterate through the waypoints and execute autonomous flight to each waypoint
        for (String waypoint : waypoints) {
            String[] coordinates = waypoint.split(",");
            if (coordinates.length != 2) {
                continue;
            }

            double latitude = Double.parseDouble(coordinates[0].trim());
            double longitude = Double.parseDouble(coordinates[1].trim());

            // Execute autonomous flight to the current waypoint
            executeAutonomousFlightToWaypoint(latitude, longitude);

        }
    }

    /**
     * Method to execute autonomous flight to a specific waypoint
     */
    private void executeAutonomousFlightToWaypoint(double latitude, double longitude) {

    }

    /**
     * Method to locate the drone
     */
    public void findDroneUsingRangeFinder(TextArea text) {

//        double droneLatitude = getDroneCoordinates().getLatitude();
//        double droneLongitude = getDroneCoordinates().getLongitude();
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
        confirmationDialog.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        ButtonType result = confirmationDialog.showAndWait().orElse(ButtonType.CANCEL);

        // Return true if the user clicked OK, false otherwise
        return result == ButtonType.OK;
    }
}
