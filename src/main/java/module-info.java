module ru.virtual.experiment.virtualorganization {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens ru.virtual.experiment.virtualorganization to javafx.fxml;
    exports ru.virtual.experiment.virtualorganization;
    exports ru.virtual.experiment.virtualorganization.interfaces;
    opens ru.virtual.experiment.virtualorganization.interfaces to javafx.fxml;
    exports ru.virtual.experiment.virtualorganization.controllers;
    opens ru.virtual.experiment.virtualorganization.controllers to javafx.fxml;
    exports ru.virtual.experiment.virtualorganization.models;
    opens ru.virtual.experiment.virtualorganization.models to javafx.fxml;
    exports ru.virtual.experiment.virtualorganization.ComputingServices;
    opens ru.virtual.experiment.virtualorganization.ComputingServices to javafx.fxml;
}