module com.example.drsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.example.drsystem to javafx.fxml;
    exports com.example.drsystem;
    exports com.example.drsystem.controller;
    opens com.example.drsystem.controller to javafx.fxml;
}