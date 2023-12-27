package ru.virtual.experiment.virtualorganization;

import java.util.Map;

public class StaffCharacteristics implements SubjectInterface{
    // Задаем поля класса.
    private int numberOfSubjects;// Переменная для определения численности персонала.
    private int numberOfOperation;// Переменная для определения количества элементарных операций технологического процесса (среднее значение на одного сотрудника).
    Map<Integer, Employee> doubleMap;// Создаем hash map для хранения пар id сотрудника - уровень его загруженности.

    private double[][]kompetence;// Создаем двумерный массив для хранения показателей компетенций персонала.

    public StaffCharacteristics(int numberOfSubjects, int numberOfOperation) {
        setKompetence(NumberOfSubjects, NumberOfOperation);
    }

    public void StaffCharacteristics(){

    }

    public Map<Integer, Employee> getDoubleMap() {
        return doubleMap;
    }

    public void setDoubleMap(Map<Integer, Employee> doubleMap) {

        this.doubleMap = doubleMap;

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
    public int getIdMaxKompetence(int id) { // Метод принимает номер операции на входе и возвращает номер сотрудника с максимальной компетенцией по этой операции.
        double max = kompetence[0][id];
        int idEmployee = 0;
        for(int i = 0; i < numberOfSubjects; i++){
            if(max < kompetence[i][id]){
                max = kompetence[i][id];
                idEmployee = i;
            }
        }
        return idEmployee;
    }

    @Override
    public double getEmployeeKompetence(int idEmployee, int idOperation) {
        double employeeKompetence = kompetence[idEmployee][idOperation];
        return employeeKompetence;
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

    public void setKompetence(double[][] kompetence) {
        this.kompetence = kompetence;
    }

    @Override
    public double employeeWorkloadTime(double directWorkTime, double PlanningHorizon, double employeeKompetence, int idOperation, int idEmployee) {
        double empWorkT = directWorkTime + directWorkTime * (1 - employeeKompetence);
        double realEmployeeWorkloadTime = doubleMap.get(idEmployee).getRealEmployeeWorkloadTime() + empWorkT;
        Employee emp = new Employee();
        emp.setRealEmployeeWorkloadTime(realEmployeeWorkloadTime);
        double loadFactor = realEmployeeWorkloadTime / PlanningHorizon;
        emp.setLoadFactor(loadFactor);
        doubleMap.put(idEmployee, emp);
        return empWorkT;
    }

    @Override
    public double getLoadFactor(int idEmployee) {

        return doubleMap.get(idEmployee).getLoadFactor();
    }

}
