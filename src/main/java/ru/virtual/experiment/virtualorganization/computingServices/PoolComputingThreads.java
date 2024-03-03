package ru.virtual.experiment.virtualorganization.computingServices;

public class PoolComputingThreads { // Класс предназначен для расчета полей данных для отрисовки 3-х мерных графиков
   private int numberOfSubjects;// Здесь устанавливается предел численности персонала для расчетов.
    private int numberOfOperation;// Здесь устанавливается предел количества элементарных операций технологического процесса (среднее значение на одного сотрудника).
    private double planningHorizon;// Создается переменная для определения горизонта планирования.
    private double[][]resultMatrixDuration; // Результирующий массив для записи данных длительности выполнения совокупности оперций.
    private double[][]resultMatrixNeedForEmployees; // Результирующий массив для записи данных о текущей потребности в сотрудниках.
    private double[][]resultMatrixRealDuration; // Результирующий массив для записи данных  длительности выполнения совокупности оперций
    // при назначении на работы компетентных сотрудников.
    int cores = Runtime.getRuntime().availableProcessors();// Получаем количество ядер процессора на используемом компьютере.

    Object lock1 = new Object();
    Object lock2 = new Object();
    Object lock3 = new Object();
    PoolComputingThreads(){

    }
    public PoolComputingThreads(int numberOfSubjects, int numberOfOperation, double planningHorizon){
        this.numberOfSubjects = numberOfSubjects;
        this.numberOfOperation = numberOfOperation;
        this.planningHorizon = planningHorizon;
        resultMatrixDuration = new double[numberOfSubjects][numberOfOperation]; // Результирующий массив для записи данных длительности выполнения совокупности оперций.
        resultMatrixNeedForEmployees = new double[numberOfSubjects][numberOfOperation]; // Результирующий массив для записи данных о текущей потребности в сотрудниках.
        resultMatrixRealDuration = new double[numberOfSubjects][numberOfOperation]; // Результирующий массив для записи данных  длительности выполнения совокупности оперций
        // при назначении на работы компетентных сотрудников.
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
    public Runnable fillingInTheMatrix(int numberOfSubjects, int numberOfOperation){

        double value;
        WorkLoadComputingService workLoadComputingService = new WorkLoadComputingService(numberOfSubjects, numberOfOperation, planningHorizon);


        synchronized(lock1){
            value = workLoadComputingService.calculateArandomTimeBudget();
            System.out.println("======" + value);
            resultMatrixDuration[numberOfSubjects][numberOfOperation] = value; // Заполняется матрица resultMatrixDuration значениями продолжительности выполнения совокупности операций.
        }
        synchronized(lock2){
            value = workLoadComputingService.theNeedForEmployees();
            resultMatrixNeedForEmployees[numberOfSubjects][numberOfOperation] = value; // Заполняется матрица resultMatrixNeedForEmployees значениями текущей потребности в сотрудниках.
        }
        synchronized(lock3){
            value = workLoadComputingService.getRealBudgetWorkTime();
            resultMatrixRealDuration[numberOfSubjects][numberOfOperation] = value; // Заполняется матрица resultMatrixRealDuration значениями продолжительности выполнения совокупности операций
            // при назначении на работы компетентных сотрудников.
        }

        return null;
    }

}

