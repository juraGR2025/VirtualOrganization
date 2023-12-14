package ru.virtual.experiment.virtualorganization;

import java.util.Map;

public class StaffCharacteristics implements SubjectInterface{
    // Задаем поля класса.
    private int numberOfSubjects;// Переменная для определения численности персонала.
    private int numberOfOperation;// Переменная для определения количества элементарных операций технологического процесса (среднее значение на одного сотрудника).
    Map<Integer, Double> doubleMap;// Создаем hash map для хранения пар id сотрудника - уровень его загруженности.
    private double[][]kompetence;// Создаем двумерный массив для хранения показателей компетенций персонала.

    public void StaffCharacteristics(){

    }
    public void StaffCharacteristics(int NumberOfSubjects, int NumberOfOperation){
        setKompetence(NumberOfSubjects, NumberOfOperation);
    }
    @Override
    public void setNumberOfSubjects(int NumberOfSubjects) {
    this.numberOfSubjects = NumberOfSubjects;
    }

    @Override
    public int getNumberOfSubjects() {
    return this.numberOfSubjects;
    }

    public int getNumberOfOperation() {
        return this.numberOfOperation;
    }

    @Override
    public void setKompetence(int NumberOfSubjects, int NumberOfOperation) {
        kompetence = new double[NumberOfSubjects][NumberOfOperation * NumberOfSubjects];
        for(int i = 0; i < NumberOfSubjects; i++){
            for(int j = 0; j < NumberOfSubjects * NumberOfSubjects; j++){
                kompetence[i][j] = Math.random();
            }
        }
    }

    @Override
    public double getMaxKompetence(int id) {
        double max = kompetence[0][id];
        for(int i = 0; i < numberOfSubjects; i++){
            if(max < kompetence[i][id]){
                max = kompetence[i][id];
            }
        }
        return max;
    }

    @Override
    public double getMinKompetence(int id) {
        double min = kompetence[0][id];
        for(int i = 0; i < numberOfSubjects; i++){
            if(min > kompetence[i][id]){
                min = kompetence[i][id];
            }
        }
        return min;
    }

    @Override
    public double employeeWorkloadTime(double directWorkTime, double employeeKompetence) {
        return directWorkTime + directWorkTime * (1 - employeeKompetence);
    }

    @Override
    public double getLoadFactor(int idEmployee, double PlanningHorizon) {
        return doubleMap.get(idEmployee) / PlanningHorizon;
    }

}
