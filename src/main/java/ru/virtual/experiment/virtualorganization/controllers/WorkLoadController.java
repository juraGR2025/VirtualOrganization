package ru.virtual.experiment.virtualorganization.controllers;

import ru.virtual.experiment.virtualorganization.models.BusinessCharacteristics;
import ru.virtual.experiment.virtualorganization.models.StaffCharacteristics;

public class WorkLoadController {

    private int iterationNumb = 100000;// Устанавливаем количество итераций для данных виртуального эксперимента.
    private double planningHorizon;// Создается переменная для определения горизонта планирования.
    private int numberOfSubjects;// Переменная для определения численности персонала.
    private int numberOfOperation;// Переменная для определения количества элементарных операций технологического процесса (среднее значение на одного сотрудника).
    private double budgetWorkTime;// Переменная для определения бюджета рабочего времени организации в целом.
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

// Далее происходит расчет показателей делового процесса:

    int cores = Runtime.getRuntime().availableProcessors();// Получаем количество ядер в процессоре.

// Расчитываем продолжительность выполнения операций компетентными сотрудниками.
    public double getRealBudgetTime(){
        double realBudgetTime = 0;
        double [] duration = business.getDurationMap();

        for(int i = 0; i < numberOfOperation * numberOfSubjects; i++) {
            System.out.print(duration[i] + " ");
        }
        System.out.println();
        double sum = 0;
        for (int i = 0; i < numberOfOperation * numberOfSubjects; i++) {
            sum = sum + duration[i];
        }
        System.out.println("The amount: " + sum);

        for(int i = 0; i < numberOfOperation * numberOfSubjects; i++){
            realBudgetTime = realBudgetTime + duration[i] + duration[i] * staff.getEmployeeKompetence(staff.getIdMaxKompetence(i), i);
// Метод заполняет Map<Integer, Employee> doubleMap.
            staff.employeeWorkloadTime(duration[i], planningHorizon, staff.getEmployeeKompetence(staff.getIdMaxKompetence(i), i), i, staff.getIdMaxKompetence(i));

    }
        business.setRealWorkLoad(numberOfOperation * numberOfSubjects);
        realBudgetTime = realBudgetTime + business.getRealWorkLoad();

        return realBudgetTime;// Метод возвращает реальный бюджет времени, затраченный на выполнение рабочих операций.
    }

}
