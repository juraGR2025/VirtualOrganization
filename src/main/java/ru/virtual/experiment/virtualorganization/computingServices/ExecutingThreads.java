package ru.virtual.experiment.virtualorganization.computingServices;

import ru.virtual.experiment.virtualorganization.models.MatrixResult;

public class ExecutingThreads implements Runnable { // Выполняемая нить.

    private int numberOfSubjects;
    private int numberOfOperation;
    private double planningHorizon;// Создается переменная для определения горизонта планирования.
    private WorkLoadComputingService workLoadComputingService; //
    private MatrixResult matrixResult;

    final Object lock1 = new Object();
    final Object lock2 = new Object();
    final Object lock3 = new Object();

    public ExecutingThreads(int numberOfSubjects, int numberOfOperation, double planningHorizon) {
        this.numberOfSubjects = numberOfSubjects;
        this.numberOfOperation = numberOfOperation;
        this.planningHorizon = planningHorizon;
        matrixResult = new MatrixResult(numberOfSubjects, numberOfOperation, planningHorizon);
    }

    @Override
    public void run() {

        for (int i = 0; i < numberOfSubjects; i++) {

            for (int j = 0; j < numberOfOperation; j++) {

                    workLoadComputingService = new WorkLoadComputingService(i, j, planningHorizon);

                    matrixResult.fillingInTheResultMatrixDuration(i, j, workLoadComputingService.getRealBudgetWorkTime());

                    matrixResult.fillingInTheMatrixNeedForEmployees(i, j, workLoadComputingService.theNeedForEmployees());

                    matrixResult.fillingInTheMatrixRealDuration(i, j, workLoadComputingService.getBudgetWorkTime());
                }
            }
        matrixResult.printMatrixDuration();
    }
}
