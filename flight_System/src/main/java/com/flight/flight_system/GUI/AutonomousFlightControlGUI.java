package com.flight.flight_system.GUI;

import com.flight.flight_system.Data.FlightOperations;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AutonomousFlightControlGUI extends Application {

    private Button takeoffButton;
    private Button landButton;
    private Button startMissionButton;
    private Button moveForwardButton;
    private Button turnLeftButton;
    private Button turnRightButton;
    private Button setWaypointsButton; // New button for setting waypoints
    private Button startLiveVideoButton;
    private Button cancelLiveVideoButton;
    private TextArea waypointsTextArea; // Text area for entering waypoints coordinates
    private ImageView cameraImageView;

    private final FlightOperations operations = new FlightOperations();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Autonomous Flight Control");

        // Create labels
        Label titleLabel = new Label("Drone Control Panel");
        titleLabel.setFont(Font.font("Arial", 24));

        Label infoLabel = new Label("Click the buttons to control the drone:");
        infoLabel.setFont(Font.font("Arial", 14));

        // Setting text to buttons
        takeoffButton = new Button("Takeoff");
        landButton = new Button("Land");
        startMissionButton = new Button("Start Mission");
        moveForwardButton = new Button("Move Forward");
        turnLeftButton = new Button("Turn Left");
        turnRightButton = new Button("Turn Right");
        setWaypointsButton = new Button("Set Waypoints"); // New button for setting waypoints
        startLiveVideoButton = new Button("Start Live Video");
        cancelLiveVideoButton = new Button("Cancel Live Video");

        // Attach event handlers to the buttons
        takeoffButton.setOnAction(e -> operations.takeoff());
        landButton.setOnAction(e -> operations.land());
        startMissionButton.setOnAction(e -> operations.startMission());
        moveForwardButton.setOnAction(e -> operations.moveForward());
        turnLeftButton.setOnAction(e -> operations.turnLeft());
        turnRightButton.setOnAction(e -> operations.turnRight());
        setWaypointsButton.setOnAction(e -> operations.setWaypoints()); // Event handler for setting waypoints

        // Create the camera feed ImageView (placeholder image)
        cameraImageView = new ImageView(new Image("/drone.jpg"));
        cameraImageView.setFitWidth(200);
        cameraImageView.setFitHeight(150);

        // Text area for entering waypoints coordinates
        waypointsTextArea = new TextArea();
        waypointsTextArea.setPromptText("Enter waypoints coordinates (latitude, longitude) separated by commas");

        // Create GridPane for buttons
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add buttons to the grid
        gridPane.add(takeoffButton, 0, 0);
        gridPane.add(landButton, 1, 0);
        gridPane.add(startMissionButton, 0, 1);
        gridPane.add(moveForwardButton, 1, 1);
        gridPane.add(turnLeftButton, 0, 2);
        gridPane.add(turnRightButton, 1, 2);
        gridPane.add(setWaypointsButton, 0, 3, 2, 1); // Set Waypoints button spans 2 columns

        // Create HBox for live video buttons
        HBox liveVideoButtonsHBox = new HBox(10);
        liveVideoButtonsHBox.setAlignment(Pos.CENTER);
        liveVideoButtonsHBox.getChildren().addAll(startLiveVideoButton, cancelLiveVideoButton);

        // Create VBox for the camera image and live video buttons
        VBox cameraVBox = new VBox(10);
        cameraVBox.setAlignment(Pos.CENTER);
        cameraVBox.getChildren().addAll(cameraImageView, liveVideoButtonsHBox);

        // Add components to the layout
        VBox leftLayout = new VBox(10);
        leftLayout.setAlignment(Pos.CENTER);
        leftLayout.setPadding(new Insets(20));
        leftLayout.getChildren().addAll(
                titleLabel, infoLabel, gridPane, waypointsTextArea // Use the gridPane instead of adding buttons individually
        );

        HBox layout = new HBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(leftLayout, cameraVBox);

        // Create the scene and set it on the stage
        Scene scene = new Scene(layout, 800, 350); // Increased height to accommodate the waypointsTextArea
        stage.setScene(scene);

        // Show the stage
        stage.show();
    }
}
