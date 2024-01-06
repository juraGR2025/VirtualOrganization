package ru.virtual.experiment.virtualorganization;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.virtual.experiment.virtualorganization.controllers.WorkLoadController;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 340);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        WorkLoadController workLoad = new WorkLoadController(10, 10, 300.0);
        launch();
        String str = "Work completion time: " + workLoad.getRealBudgetTime();
        System.out.println(str);
    }

}