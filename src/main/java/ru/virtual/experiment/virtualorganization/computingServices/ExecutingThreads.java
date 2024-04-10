package ru.virtual.experiment.virtualorganization.computingServices;

import ru.virtual.experiment.virtualorganization.models.MatrixResult;

public class ExecutingThreads implements Runnable{

    private int numberOfSubjects;
    private int numberOfOperation;
    private double planningHorizon;// Создается переменная для определения горизонта планирования.
    private MatrixResult matrixResult;
    int id; //
    private int cores;

    final Object lock1 = new Object();
    Object lock2 = new Object();
    Object lock3 = new Object();

    public ExecutingThreads(int id, int numberOfSubjects, int numberOfOperation, double planningHorizon){
        this.id = id;
        this.numberOfSubjects = numberOfSubjects;
        this.numberOfOperation = numberOfOperation;
        this.planningHorizon = planningHorizon;
        matrixResult = new MatrixResult(numberOfSubjects, numberOfOperation, planningHorizon);

    }

        @Override
    public void run() {
                WorkLoadComputingService workLoadComputingService = new WorkLoadComputingService(numberOfSubjects, numberOfOperation, planningHorizon);
                for (int i = 0; i < numberOfSubjects; i++) {
                    for (int j = 0; j < numberOfOperation; j++) {
                        synchronized (lock1) {
                           matrixResult.fillingInTheResultMatrixDuration(numberOfSubjects, numberOfOperation, workLoadComputingService.getRealBudgetWorkTime());
                        }
                    }
                }
    }
}
