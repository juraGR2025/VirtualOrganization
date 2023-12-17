package ru.virtual.experiment.virtualorganization;

public class WorkLoadController {

    private int iterationNumb = 100000;// Устанавливаем количество итераций для данных виртуального эксперимента.
    private double planningHorizon;// Создается переменная для определения горизонта планирования.
    private int numberOfSubjects;// Переменная для определения численности персонала.
    private int numberOfOperation;// Переменная для определения количества элементарных операций технологического процесса (среднее значение на одного сотрудника).
    private double budgetWorkTime;

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
// Создаем экземпляры классов StaffCharacteristics и BusinessCharacteristics для определения параметров модели.

    StaffCharacteristics staff = new StaffCharacteristics(numberOfSubjects, numberOfOperation);
    BusinessCharacteristics business = new BusinessCharacteristics(numberOfSubjects, planningHorizon, numberOfOperation);
// Далее происходит расчет показателей делового процесса:

}
