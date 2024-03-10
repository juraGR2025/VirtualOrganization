package ru.virtual.experiment.virtualorganization.models;

public class MatrixResult { // Класс предназначен для расчета полей данных для отрисовки 3-х мерных графиков
    private int numberOfSubjects;// Здесь устанавливается предел численности персонала для расчетов.
    private int numberOfOperation;// Здесь устанавливается предел количества элементарных операций технологического процесса (среднее значение на одного сотрудника).
    private double planningHorizon;// Создается переменная для определения горизонта планирования.
    private double[][]resultMatrixDuration; // Результирующий массив для записи данных длительности выполнения совокупности оперций.
    private double[][]resultMatrixNeedForEmployees; // Результирующий массив для записи данных о текущей потребности в сотрудниках.
    private double[][]resultMatrixRealDuration; // Результирующий массив для записи данных  длительности выполнения совокупности оперций
    // при назначении на работы компетентных сотрудников.

    MatrixResult(){}

    public MatrixResult(int numberOfSubjects, int numberOfOperation, double planningHorizon){
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
    public void fillingInTheResultMatrixDuration(int numberOfSubjects, int numberOfOperation, double duration){
            resultMatrixDuration[numberOfSubjects][numberOfOperation] = duration;// Заполняется матрица resultMatrixDuration значениями продолжительности выполнения совокупности операций.
        }
    public void fillingInTheMatrixNeedForEmployees(int numberOfSubjects, int numberOfOperation, double needForEmployees){
             resultMatrixNeedForEmployees[numberOfSubjects][numberOfOperation] = needForEmployees; // Заполняется матрица resultMatrixNeedForEmployees значениями текущей потребности в сотрудниках.
        }
    public void fillingInTheMatrixRealDuration(int numberOfSubjects, int numberOfOperation, double realDuration){
            resultMatrixRealDuration[numberOfSubjects][numberOfOperation] = realDuration; // Заполняется матрица resultMatrixRealDuration значениями продолжительности выполнения совокупности операций
            // при назначении на работы компетентных сотрудников.
        }

}

