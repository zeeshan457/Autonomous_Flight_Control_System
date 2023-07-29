module com.flight.flight_system {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.flight.flight_system to javafx.fxml;
    exports com.flight.flight_system;
    exports com.flight.flight_system.GUI; // Export the GUI package
}