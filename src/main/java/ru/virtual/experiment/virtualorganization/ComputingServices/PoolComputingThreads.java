package ru.virtual.experiment.virtualorganization.ComputingServices;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PoolComputingThreads { // Класс предназначен для расчета полей данных для отрисовки 3-х мерных графиков
   private int numberOfSubjects;// Здесь устанавливается предел численности персонала для расчетов.
    private int numberOfOperation;// Здесь устанавливается предел количества элементарных операций технологического процесса (среднее значение на одного сотрудника).
    private double planningHorizon;// Создается переменная для определения горизонта планирования.
    private double[][]resultMatrixDuration = new double[numberOfSubjects][numberOfOperation]; // Результирующий массив для записи данных длительности выполнения совокупности оперций.
    private double[][]resultMatrixNeedForEmployees = new double[numberOfSubjects][numberOfOperation]; // Результирующий массив для записи данных о текущей потребности в сотрудниках.
    private double[][]resultMatrixRealDuration = new double[numberOfSubjects][numberOfOperation]; // Результирующий массив для записи данных  длительности выполнения совокупности оперций
    // при назначении на работы компетентных сотрудников.
    int cores = Runtime.getRuntime().availableProcessors();// Получаем количество ядер процессора на используемом компьютере.
    PoolComputingThreads(){

    }
    PoolComputingThreads(int numberOfSubjects, int numberOfOperation, double planningHorizon){
        this.numberOfSubjects = numberOfSubjects;
        this.numberOfOperation = numberOfOperation;
        this.planningHorizon = planningHorizon;
    }
    public int getNumberOfSubjects() {
        return numberOfSubjects;
    }

    public void setNumberOfSubjects(int numberOfSubjects) {
        this.numberOfSubjects = numberOfSubjects;
    }

    public int getNumberOfOperation() {
        return numberOfOperation;
    }

    public void setNumberOfOperation(int numberOfOperation) {
        this.numberOfOperation = numberOfOperation;
    }
    public synchronized void fillingInTheMatrix(int numberOfSubjects, int numberOfOperation){
        double value;
        WorkLoadComputingService workLoadComputingService =  new WorkLoadComputingService(numberOfSubjects, numberOfOperation, planningHorizon);
        value = workLoadComputingService.calculateArandomTimeBudget();
        resultMatrixDuration[numberOfSubjects][numberOfOperation] = value; // Заполняется матрица resultMatrixDuration значениями продолжительности выполнения совокупности операций.
        value = workLoadComputingService.theNeedForEmployees();
        resultMatrixNeedForEmployees[numberOfSubjects][numberOfOperation] = value; // Заполняется матрица resultMatrixNeedForEmployees значениями текущей потребности в сотрудниках.
        value = workLoadComputingService.getRealBudgetWorkTime();
        resultMatrixRealDuration[numberOfSubjects][numberOfOperation] = value; // Заполняется матрица resultMatrixRealDuration значениями продолжительности выполнения совокупности операций
        // при назначении на работы компетентных сотрудников.
    }
    class ParallelComputingClass implements Runnable{
        int numberOfSubjects;
        int numberOfOperation;
        ParallelComputingClass(int numberOfSubjects, int numberOfOperation){
            this.numberOfSubjects = numberOfSubjects;
            this.numberOfOperation = numberOfOperation;
            PoolComputingThreads pool = new PoolComputingThreads(numberOfSubjects, numberOfOperation, planningHorizon);
            pool.fillingInTheMatrix(numberOfSubjects, numberOfOperation);
        }

        @Override
        public void run() {

            ParallelComputingClass computing = new ParallelComputingClass(numberOfSubjects, numberOfOperation);

        }
    }
public void executingThreads() { // Метод для вычисления итоговых значений resultMatrixDuration, resultMatrixNeedForEmployees, resultMatrixRealDuration
        //при помощи пула потоков.
    ExecutorService executorService = Executors.newFixedThreadPool(cores); // Количество потоков должно быть не меньше, чем количество ядер процессора.

    for (int i = 0; i < numberOfSubjects; i++) {
        for (int j = 0; j < numberOfOperation; j++) {
            executorService.submit(new ParallelComputingClass(i, j));
        }
    }
            executorService.shutdown();
    try {
        executorService.awaitTermination(1, TimeUnit.DAYS);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}
}

