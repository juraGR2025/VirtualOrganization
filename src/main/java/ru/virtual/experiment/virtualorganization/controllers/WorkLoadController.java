package ru.virtual.experiment.virtualorganization.controllers;

import ru.virtual.experiment.virtualorganization.models.BusinessCharacteristics;
import ru.virtual.experiment.virtualorganization.models.StaffCharacteristics;

public class WorkLoadController {

    private int iterationNumb = 100000;// Устанавливаем количество итераций для данных виртуального эксперимента.
    private double planningHorizon;// Создается переменная для определения горизонта планирования.
    private int numberOfSubjects;// Переменная для определения численности персонала.
    private int numberOfOperation;// Переменная для определения количества элементарных операций технологического процесса (среднее значение на одного сотрудника).
    private double budgetWorkTime;// Переменная для определения бюджета рабочего времени организации в целом.
    private double realBudgetWorkTime;// Переменная для определения реального (с учетом компетентности персонала) бюджета рабочего времени организации в целом.
    StaffCharacteristics staff; // Объявляем в качестве переменных экземпляры классов StaffCharacteristics
    BusinessCharacteristics business; // и BusinessCharacteristics.
    // Далее пустой конструктор.
    public WorkLoadController() {

    }

    // Конструктор с параметрами.
    public WorkLoadController(int numberOfSubjects, int numberOfOperation, double planningHorizon) {
        this.budgetWorkTime = planningHorizon * numberOfSubjects;
        this.planningHorizon = planningHorizon;
        this.numberOfSubjects = numberOfSubjects;
        this.numberOfOperation = numberOfOperation;

        // Создаем экземпляры классов StaffCharacteristics и BusinessCharacteristics для определения параметров модели.
        staff = new StaffCharacteristics(numberOfSubjects, numberOfOperation);
        business = new BusinessCharacteristics(numberOfSubjects, planningHorizon, numberOfOperation);
        business.setRealWorkLoad(numberOfOperation * numberOfSubjects);
        this.calculateRealBudgetTime();// Обращаемся к методу для расчета realBudgetWorkTime.

    }

    public double getBudgetWorkTime() {
        return budgetWorkTime;
    }

    public void setBudgetWorkTime(double planningHorizon) {
        this.budgetWorkTime = planningHorizon * numberOfSubjects;
    }

    public int getIterationNumb() {
        return iterationNumb;
    }

    public void setIterationNumb(int iterationNumb) {
        this.iterationNumb = iterationNumb;
    }

    public double getPlanningHorizon() {
        return planningHorizon;
    }

    public void setPlanningHorizon(double planningHorizon) {
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

    public double getRealBudgetWorkTime() {
        return realBudgetWorkTime;
    }

    public void setRealBudgetWorkTime(double realBudgetWorkTime) {
        this.realBudgetWorkTime = realBudgetWorkTime;
    }

// Далее происходит расчет показателей делового процесса:

    int cores = Runtime.getRuntime().availableProcessors();// Получаем количество ядер в процессоре.

// Расчитываем продолжительность выполнения операций компетентными сотрудниками.
    public void calculateRealBudgetTime(){
        double realBudgetTime = 0;
        int idMaxKompetence = 0;
        double [] duration = business.getDurationMap();

        for(int i = 0; i < (numberOfOperation * numberOfSubjects); i++){

            idMaxKompetence = staff.getIdMaxKompetence(i);
            realBudgetTime = realBudgetTime + duration[i] + duration[i] * (1 - staff.getEmployeeKompetence(idMaxKompetence, i));

// Метод заполняет Map<Integer, Employee> doubleMap.
            staff.employeeWorkloadTime(duration[i], planningHorizon, staff.getEmployeeKompetence(idMaxKompetence, i), i, idMaxKompetence);
    }
            staff.getDoubleMap();
            realBudgetTime = realBudgetTime + business.getRealWorkLoad();

        this.setRealBudgetWorkTime(realBudgetTime);
    }
    public double calculateArandomTimeBudget(){

        double randomTimeBudget = 0;
        int idRandomKompetence = 0;
        double [] duration = business.getDurationMap();

        for(int i = 0; i < (numberOfOperation * numberOfSubjects); i++){

            idRandomKompetence = staff.getRandomKompetence(i);
            randomTimeBudget = randomTimeBudget + duration[i] + duration[i] * (1 - staff.getEmployeeKompetence(idRandomKompetence, i));

// Метод заполняет Map<Integer, Employee> doubleMap.
            staff.employeeWorkloadTime(duration[i], planningHorizon, staff.getEmployeeKompetence(idRandomKompetence, i), i, idRandomKompetence);
        }
        staff.getDoubleMap();
        randomTimeBudget = randomTimeBudget + business.getRealWorkLoad();

        return randomTimeBudget;
    }

    public int getExceedingBudgetTimeAsPercentage(){
     int percentage = 0;
        percentage = (int) ((this.getRealBudgetWorkTime() / this.budgetWorkTime) * 100);
        System.out.println("percentage: " + percentage);
    return percentage;
    }

    public double theNeedForEmployees(){
        double needForEmployees = 0;

        needForEmployees = this.realBudgetWorkTime / this.planningHorizon;

        return needForEmployees;
    }

    public double averageValueOfCompetenceIndicator(){
        double averageValue = 0;
        int idMaxKompetence = 0;
        for(int i = 0; i < (numberOfOperation * numberOfSubjects); i++){
            idMaxKompetence = staff.getIdMaxKompetence(i);
            averageValue += staff.getEmployeeKompetence(idMaxKompetence, i);
         }
        averageValue = averageValue / (numberOfOperation * numberOfSubjects);
        System.out.println("Средняя компетентность персонала: " + averageValue);
        return averageValue;
    }

}
