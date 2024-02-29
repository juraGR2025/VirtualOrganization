package ru.virtual.experiment.virtualorganization;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.virtual.experiment.virtualorganization.computingServices.ExecutingThreads;
import ru.virtual.experiment.virtualorganization.computingServices.PoolComputingThreads;
import ru.virtual.experiment.virtualorganization.computingServices.WorkLoadComputingService;

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
        int numberOfSubjects = 10;
        int numberOfOperation = 1000;
        double planningHorizon = 20;


        launch();
        WorkLoadComputingService workLoad = new WorkLoadComputingService(numberOfSubjects, numberOfOperation, planningHorizon);
        ExecutingThreads executing = new ExecutingThreads(numberOfSubjects, numberOfOperation, planningHorizon);
        executing.run();
        String str = "Work completion time: " + workLoad.getRealBudgetWorkTime();
        System.out.println(str);
        System.out.println("Бюджет рабочего времени: " + (planningHorizon * numberOfSubjects));
        workLoad.getExceedingBudgetTimeAsPercentage();
        System.out.println("Потребность в персонале: " + workLoad.theNeedForEmployees());
        workLoad.averageValueOfCompetenceIndicator();
        System.out.println("Длительность выполнения операций при случайном распределении персонала: " + workLoad.calculateArandomTimeBudget());
        System.out.println("Потребность в персонале при его случайном распределении: " + workLoad.theNeedForEmployeesRandom());


    }

}