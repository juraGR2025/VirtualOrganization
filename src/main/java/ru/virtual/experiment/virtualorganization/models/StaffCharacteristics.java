package ru.virtual.experiment.virtualorganization.models;

import ru.virtual.experiment.virtualorganization.interfaces.SubjectInterface;

import java.util.HashMap;
import java.util.Map;

public class StaffCharacteristics implements SubjectInterface {
    // Задаем поля класса.
    private int numberOfSubjects;// Переменная для определения численности персонала.
    private int numberOfOperation;// Переменная для определения количества элементарных операций технологического процесса (среднее значение на одного сотрудника).
    Map<Integer, Employee> doubleMap = new HashMap<>();// Создаем hash map для хранения пар id сотрудника - уровень его загруженности.

    private double[][]kompetence;// Создаем двумерный массив для хранения показателей компетенций персонала.

    public StaffCharacteristics(int numberOfSubjects, int numberOfOperation) {
        this.numberOfSubjects = numberOfSubjects;
        this.numberOfOperation = numberOfOperation;
        this.setKompetence(numberOfSubjects, numberOfOperation);
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
    public void setNumberOfSubjects(int numberOfSubjects) {
    this.numberOfSubjects = numberOfSubjects;
    }

    @Override
    public int getNumberOfSubjects() {
    return this.numberOfSubjects;
    }

    public int getNumberOfOperation() {
        return this.numberOfOperation;
    }

    @Override
    public void setKompetence(int numberOfSubjects, int numberOfOperation) {
        kompetence = new double[numberOfSubjects][numberOfOperation * numberOfSubjects];
        for(int i = 0; i < numberOfSubjects; i++){
            for(int j = 0; j < numberOfSubjects * numberOfOperation; j++){
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

    @Override
    public double employeeWorkloadTime(double directWorkTime, double PlanningHorizon, double employeeKompetence, int idOperation, int idEmployee) {
        double empWorkT = directWorkTime + directWorkTime * (1 - employeeKompetence);
        double realEmployeeWorkloadTime = 0;
        Employee emp = new Employee();

        if(doubleMap.containsKey(idEmployee)){// Проверяем наличие ключа idEmployee в doubleMap.
            realEmployeeWorkloadTime = doubleMap.get(idEmployee).getRealEmployeeWorkloadTime() + empWorkT;
        }
        else{// Если ключа нет, получаем значение загруженности для нового объекта класса Employee.
            realEmployeeWorkloadTime = empWorkT;
        }

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
