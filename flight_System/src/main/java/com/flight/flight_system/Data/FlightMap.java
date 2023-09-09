package com.flight.flight_system.Data;

import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class FlightMap {

    public void showMapOnBrowser() {
        // Replace the latitude and longitude with the actual drone's location
        double latitude = 37.7749;
        double longitude = -122.4194;

        // Google Maps URL with the drone's latitude and longitude
        String mapsURL = "https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude;

        try {
            Desktop.getDesktop().browse(new URI(mapsURL));
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
            // Handle the exception if the browser cannot be opened
        }
    }
}
