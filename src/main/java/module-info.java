module com.example.airlinecompanymanagment {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens com.example.airlinecompanymanagment to javafx.fxml;
    exports com.example.airlinecompanymanagment;
    exports com.example.airlinecompanymanagment.controllers;
    exports com.example.airlinecompanymanagment.models;
    opens com.example.airlinecompanymanagment.models to javafx.fxml;
    opens com.example.airlinecompanymanagment.controllers to javafx.fxml;
}