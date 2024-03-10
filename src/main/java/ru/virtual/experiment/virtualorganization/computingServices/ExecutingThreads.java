package ru.virtual.experiment.virtualorganization.computingServices;

import ru.virtual.experiment.virtualorganization.models.MatrixResult;

public class ExecutingThreads implements Runnable{

    private int numberOfSubjects;
    private int numberOfOperation;
    private double planningHorizon;// Создается переменная для определения горизонта планирования.
    private MatrixResult matrixResult;
    private int cores;

    Object lock1 = new Object();
    Object lock2 = new Object();
    Object lock3 = new Object();

    public ExecutingThreads(int numberOfSubjects, int numberOfOperation, double planningHorizon){
        this.numberOfSubjects = numberOfSubjects;
        this.numberOfOperation = numberOfOperation;
        this.planningHorizon = planningHorizon;
        matrixResult = new MatrixResult(numberOfSubjects, numberOfOperation, planningHorizon);

    }

    public void executingThreads() { // Метод для вычисления итоговых значений resultMatrixDuration, resultMatrixNeedForEmployees, resultMatrixRealDuration
        //при помощи пула потоков.
        for (int i = 0; i < numberOfSubjects; i++) {
            for (int j = 0; j < numberOfOperation; j++) {
                fillingInTheMatrix(i, j);
            }
        }
    }
    // Метод, который отвечает за заполнение матриц класса PoolComputingThreads - double[][]resultMatrixDuration, double[][]resultMatrixNeedForEmployees, double[][]resultMatrixRealDuration;
    public void fillingInTheMatrix(int numberOfSubjects, int numberOfOperation) {
        WorkLoadComputingService workLoadComputingService = new WorkLoadComputingService(numberOfSubjects, numberOfOperation, planningHorizon);
        synchronized (lock1) {
            matrixResult.fillingInTheResultMatrixDuration(numberOfSubjects, numberOfOperation, workLoadComputingService.getBudgetWorkTime());
        }
    }
        @Override
    public void run() {
        executingThreads();

    }
}
