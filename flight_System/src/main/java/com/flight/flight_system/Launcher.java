package com.flight.flight_system;

import com.flight.flight_system.GUI.AutonomousFlightControlGUI;
import javafx.application.Application;

public class Launcher {

    /**
     * Launching the system UI from this class.
     * @param args main class
     */
    public static void main(String[] args) {
        Application.launch(AutonomousFlightControlGUI.class, args);
    }
}
