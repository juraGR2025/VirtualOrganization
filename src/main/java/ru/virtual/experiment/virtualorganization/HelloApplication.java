package ru.virtual.experiment.virtualorganization;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.virtual.experiment.virtualorganization.ComputingServices.WorkLoadComputingService;

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
        WorkLoadComputingService workLoad = new WorkLoadComputingService(10, 50000, 20.0);
        launch();

        String str = "Work completion time: " + workLoad.getRealBudgetWorkTime();
        System.out.println(str);
        workLoad.getExceedingBudgetTimeAsPercentage();
        System.out.println("Потребность в персонале: " + workLoad.theNeedForEmployees());
        workLoad.averageValueOfCompetenceIndicator();
        System.out.println("Длительность выполнения операций при случайном распределении персонала: " + workLoad.calculateArandomTimeBudget());
    }

}