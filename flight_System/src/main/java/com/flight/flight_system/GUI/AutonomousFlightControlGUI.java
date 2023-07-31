package com.flight.flight_system.GUI;

import com.flight.flight_system.Data.FlightData;
import com.flight.flight_system.Data.FlightMap;
import com.flight.flight_system.Data.FlightOperations;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AutonomousFlightControlGUI extends Application {

    /**
     * Attributes global access
     */
    private Label titleLabel;
    private Label infoLabel;
    private Label batteryLabel;
    private Label timeLabel;
    private Label weatherLabel;
    private Button takeoffButton;
    private Button landButton;
    private Button startMissionButton;
    private Button moveForwardButton;
    private Button turnLeftButton;
    private Button turnRightButton;
    private Button setWaypointsButton;
    private Button startLiveVideoButton;
    private Button cancelLiveVideoButton;
    private Button findDroneButton;
    private Button showMapButton;
    private TextArea waypointsTextArea;
    private TextArea rangeFinderOutput;
    private ImageView cameraImageView;
    private final FlightOperations operations = new FlightOperations();
    private final FlightMap map = new FlightMap();
    private final FlightData data = new FlightData();


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Autonomous Flight Control");
        // Set the application icon
        Image icon = new Image(getClass().getResourceAsStream("/drone.png"));
        stage.getIcons().add(icon);

        // Buttons for range finder and related components
        findDroneButton = new Button("Find Drone");
        rangeFinderOutput = new TextArea();
        rangeFinderOutput.setEditable(false);
        rangeFinderOutput.setPrefRowCount(5);

        // Labels
        titleLabel = new Label("Drone Control Panel");
        titleLabel.setFont(Font.font("Arial", 24));
        infoLabel = new Label("Click the buttons to control the drone:");
        infoLabel.setFont(Font.font("Arial", 14));
        batteryLabel = new Label("Battery: 100%");
        timeLabel = new Label(data.getCurrentTime());
        weatherLabel = new Label("Weather: Sunny");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), e -> updateData()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Create HBox for battery, time, and weather information
        HBox infoBox = new HBox(20);
        infoBox.setAlignment(Pos.CENTER);
        infoBox.getChildren().addAll(batteryLabel, timeLabel, weatherLabel);

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
        showMapButton = new Button("Show Map");

        // Create the camera feed ImageView (placeholder image)
        cameraImageView = new ImageView(new Image("/drone.jpg"));
        cameraImageView.setFitWidth(350);
        cameraImageView.setFitHeight(300);

        // Text area for entering waypoints coordinates
        waypointsTextArea = new TextArea();
        waypointsTextArea.setPromptText("Enter waypoints coordinates (latitude, longitude) separated by commas");

        // Create GridPane for movement-related buttons
        GridPane movementButtonsGrid = new GridPane();
        movementButtonsGrid.setAlignment(Pos.CENTER);
        movementButtonsGrid.setHgap(10);
        movementButtonsGrid.setVgap(10);
        movementButtonsGrid.add(moveForwardButton, 0, 0);
        movementButtonsGrid.add(turnLeftButton, 1, 0);
        movementButtonsGrid.add(turnRightButton, 2, 0);
        // Create GridPane for live video-related buttons
        GridPane liveVideoButtonsGrid = new GridPane();
        liveVideoButtonsGrid.setAlignment(Pos.CENTER);
        liveVideoButtonsGrid.setHgap(10);
        liveVideoButtonsGrid.setVgap(10);
        liveVideoButtonsGrid.add(startLiveVideoButton, 0, 0);
        liveVideoButtonsGrid.add(cancelLiveVideoButton, 1, 0);
        // Create GridPane for buttons
        GridPane buttonsGridPane = new GridPane();
        buttonsGridPane.setAlignment(Pos.CENTER);
        buttonsGridPane.setHgap(10);
        buttonsGridPane.setVgap(10);
        buttonsGridPane.add(takeoffButton, 0, 0);
        buttonsGridPane.add(landButton, 1, 0);
        buttonsGridPane.add(startMissionButton, 2, 0);
        // Create GridPane for range finder components
        GridPane rangeFinderGrid = new GridPane();
        rangeFinderGrid.setAlignment(Pos.CENTER);
        rangeFinderGrid.setHgap(10);
        rangeFinderGrid.setVgap(10);
        rangeFinderGrid.add(rangeFinderOutput, 0, 0);
        rangeFinderGrid.add(findDroneButton, 0, 1);
        rangeFinderGrid.add(showMapButton, 0, 2);
        GridPane wayPointGrid = new GridPane();
        wayPointGrid.setAlignment(Pos.CENTER);
        wayPointGrid.setHgap(10);
        wayPointGrid.setVgap(10);
        wayPointGrid.add(setWaypointsButton, 0, 1);
        wayPointGrid.add(waypointsTextArea, 0, 0);

        // Create TitledPane for movement-related buttons and live video-related buttons
        TitledPane movementPane = new TitledPane("Drone Movement", movementButtonsGrid);
        movementPane.setCollapsible(false);
        TitledPane droneActionPane = new TitledPane("Drone Actions", buttonsGridPane);
        droneActionPane.setCollapsible(false);
        TitledPane Options = new TitledPane("Video Options", liveVideoButtonsGrid);
        Options.setCollapsible(false);
        TitledPane liveVideoPane = new TitledPane("Live Video", cameraImageView);
        liveVideoPane.setCollapsible(false);
        TitledPane droneInfoPane = new TitledPane("Drone Information", infoBox);
        droneInfoPane.setCollapsible(false);
        TitledPane rangeFinderPane = new TitledPane("Range Finder", rangeFinderGrid);
        rangeFinderPane.setCollapsible(false);
        TitledPane wayPointsPane = new TitledPane("Set Waypoint", wayPointGrid);
        wayPointsPane.setCollapsible(false);

        // VBoxes for the panes
        VBox buttonsVBox = new VBox(10);
        buttonsVBox.setAlignment(Pos.CENTER);
        buttonsVBox.getChildren().addAll(movementPane, droneActionPane);
        VBox cameraVBox = new VBox(10);
        cameraVBox.setAlignment(Pos.CENTER);
        cameraVBox.getChildren().addAll(new Label(), liveVideoPane, Options);

        // Add the range finder pane to the leftLayout VBox
        VBox leftLayout = new VBox(10);
        leftLayout.setAlignment(Pos.CENTER);
        leftLayout.setPadding(new Insets(20));
        leftLayout.getChildren().addAll(
                droneInfoPane, titleLabel, infoLabel, buttonsVBox,
                new Label(), wayPointsPane
        );

        HBox layout = new HBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(leftLayout, cameraVBox, rangeFinderPane);

        // Scene configs and method calls
        actionEvents();
        Scene scene = new Scene(layout, 1100, 550); // Increased height to accommodate the waypointsTextArea
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Action events for buttons
     */
    public void actionEvents() {
        takeoffButton.setOnAction(e -> operations.takeoff());
        landButton.setOnAction(e -> operations.land());
        startMissionButton.setOnAction(e -> operations.startMission());
        moveForwardButton.setOnAction(e -> operations.moveForward());
        turnLeftButton.setOnAction(e -> operations.turnLeft());
        turnRightButton.setOnAction(e -> operations.turnRight());
        setWaypointsButton.setOnAction(e -> operations.setWaypoints());
        findDroneButton.setOnAction(e -> operations.findDroneUsingRangeFinder(rangeFinderOutput));
        showMapButton.setOnAction(e -> map.showMapOnBrowser());
    }

    /**
     * this method provides information about the drone and other information relevant to the time and location.
     */
    public void updateData() {
        // Update battery percentage (replace with actual data)
        int batteryPercentage = (int) (Math.random() * 100);
        batteryLabel.setText("Battery: " + batteryPercentage + "%");

        // Update time (replace with actual data)
        String currentTime = data.getCurrentTime();
        timeLabel.setText("Time: " + currentTime);
        System.out.println(currentTime);

        // Update weather (replace with actual data)
        String currentWeather = "Sunny";
        weatherLabel.setText("Weather: " + currentWeather);
    }
}
