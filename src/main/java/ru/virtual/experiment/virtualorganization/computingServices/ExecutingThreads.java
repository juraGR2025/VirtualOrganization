package ru.virtual.experiment.virtualorganization.computingServices;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutingThreads implements Runnable{

    private int numberOfSubjects;
    private int numberOfOperation;
    private double planningHorizon;// Создается переменная для определения горизонта планирования.
    private PoolComputingThreads poolComputingThreads;
    private int cores;
    public ExecutingThreads(int numberOfSubjects, int numberOfOperation, double planningHorizon){
        this.numberOfSubjects = numberOfSubjects;
        this.numberOfOperation = numberOfOperation;
        this.planningHorizon = planningHorizon;
        cores = Runtime.getRuntime().availableProcessors();// Получаем количество ядер процессора на используемом компьютере.

    }

    public void executingThreads() { // Метод для вычисления итоговых значений resultMatrixDuration, resultMatrixNeedForEmployees, resultMatrixRealDuration
        //при помощи пула потоков.
        ExecutorService executorService = Executors.newFixedThreadPool(cores); // Количество потоков должно быть не меньше, чем количество ядер процессора.

        for (int i = 0; i < numberOfSubjects; i++) {
            for (int j = 0; j < numberOfOperation; j++) {
                PoolComputingThreads poolComputingThreads = new PoolComputingThreads(i, j, planningHorizon);
                executorService.submit(poolComputingThreads.fillingInTheMatrix(i, j));
            }
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        executingThreads();
    }
}
