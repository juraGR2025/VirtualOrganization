package ru.virtual.experiment.virtualorganization.computingServices;

import ru.virtual.experiment.virtualorganization.models.BusinessCharacteristics;
import ru.virtual.experiment.virtualorganization.models.StaffCharacteristics;

public class WorkLoadComputingService {

    private int iterationNumb = 100000;// Устанавливаем количество итераций для данных виртуального эксперимента.
    private double planningHorizon;// Создается переменная для определения горизонта планирования.
    private int numberOfSubjects;// Переменная для определения численности персонала.
    private int numberOfOperation;// Переменная для определения количества элементарных операций технологического процесса (среднее значение на одного сотрудника).
    private double budgetWorkTime;// Переменная для определения бюджета рабочего времени организации в целом.
    private double realBudgetWorkTime;// Переменная для определения реального (с учетом компетентности персонала) бюджета рабочего времени организации в целом.
    private double randomBudgetWorkTime;// еременная для определения продолжительности выполнения операций с учетом случайного распределения сотрудников на работы.
    StaffCharacteristics staff; // Объявляем в качестве переменных экземпляры классов StaffCharacteristics
    BusinessCharacteristics business; // и BusinessCharacteristics.
    // Далее пустой конструктор.
    public WorkLoadComputingService() {

    }

    // Конструктор с параметрами.
    public WorkLoadComputingService(int numberOfSubjects, int numberOfOperation, double planningHorizon) {
        this.budgetWorkTime = planningHorizon * numberOfSubjects;
        this.planningHorizon = planningHorizon;
        this.numberOfSubjects = numberOfSubjects;
        this.numberOfOperation = numberOfOperation;

        // Создаем экземпляры классов StaffCharacteristics и BusinessCharacteristics для определения параметров модели.
        staff = new StaffCharacteristics(numberOfSubjects, numberOfOperation);
        business = new BusinessCharacteristics(numberOfSubjects, planningHorizon, numberOfOperation);
        business.setRealWorkLoad(numberOfOperation * numberOfSubjects);
        this.calculateRealBudgetTime();// Обращаемся к методу для расчета realBudgetWorkTime.
        this.calculateArandomTimeBudget();// Обращаемся к методу для расчета calculateArandomTimeBudget случайно выбранных сотрудников для выполнения работ.
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
        double sumOfSelectedRandomCompetencies = 0;
        int idRandomKompetence = 0;
        double [] duration = business.getDurationMap();

        for(int i = 0; i < (numberOfOperation * numberOfSubjects); i++){

            idRandomKompetence = staff.getRandomKompetence(i);
            randomTimeBudget = randomTimeBudget + duration[i] + duration[i] * (1 - staff.getEmployeeKompetence(idRandomKompetence, i));

// Метод заполняет Map<Integer, Employee> doubleMap.
            staff.employeeWorkloadTime(duration[i], planningHorizon, staff.getEmployeeKompetence(idRandomKompetence, i), i, idRandomKompetence);
// Подсчитываем сумму выбранных случайных компетенций.
            sumOfSelectedRandomCompetencies += staff.getEmployeeKompetence(idRandomKompetence, i);
        }
        staff.getDoubleMap();
        randomBudgetWorkTime = randomTimeBudget + business.getRealWorkLoad();
//System.out.println("Случайно выбранные работы выполнялись со средней компетентностью: " + sumOfSelectedRandomCompetencies / (numberOfOperation * numberOfSubjects));
        return randomBudgetWorkTime;
    }

    public int getExceedingBudgetTimeAsPercentage(){
     int percentage = 0;
        percentage = (int) ((this.getRealBudgetWorkTime() / this.budgetWorkTime) * 100);
 //       System.out.println("percentage: " + percentage);
    return percentage;
    }

    public double theNeedForEmployees(){
        double needForEmployees = 0;

        needForEmployees = this.getRealBudgetWorkTime() / this.planningHorizon;

        return needForEmployees;
    }

    public double theNeedForEmployeesRandom(){
        double needForEmployees = 0;

        needForEmployees = this.randomBudgetWorkTime / this.planningHorizon;

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
 //       System.out.println("Средняя компетентность персонала: " + averageValue);
        return averageValue;
    }
}
