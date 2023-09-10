package com.flight.flight_system.Data;

import me.friwi.tello4j.api.drone.TelloDrone;
import me.friwi.tello4j.api.exception.TelloCommandException;
import me.friwi.tello4j.api.exception.TelloNetworkException;
import me.friwi.tello4j.wifi.impl.WifiDrone;

public class FlightObstacleAvoidance {

    /**
     * Attributes
     */
    TelloDrone drone = new WifiDrone();

    /**
     * Avoiding obstacles using sensory data
     */
    public void obstacleAvoidance() {
        try {
            drone.connect();

            for (int i = 0; i < 10; i++) { // Perform obstacle avoidance for 10 iterations (adjust as needed)
                boolean obstacleDetected = checkObstacles();

                if (obstacleDetected) {
                    // If an obstacle is detected, adjust the drone's position (e.g., move up and back)
//                    TelloCommand moveUpAndBackCommand = new MoveCommand(20, 0, 0, -20); // Adjust as needed
//                    drone.sendCommand(moveUpAndBackCommand);
                } else {
                    // If no obstacle is detected, continue moving forward
//                    TelloCommand moveForwardCommand = new MoveCommand(20); // Move 20 cm forward
//                    drone.sendCommand(moveForwardCommand);
                }

                // Sleep for a short time (adjust as needed)
                Thread.sleep(1000); // Sleep for 1 second (adjust as needed)
            }


        } catch (TelloNetworkException | TelloCommandException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            drone.disconnect();
        }
    }

    // sensory data
    private boolean checkObstacles() {
        return true;
    }
}
