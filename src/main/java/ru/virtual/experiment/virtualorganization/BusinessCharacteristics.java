package ru.virtual.experiment.virtualorganization;

import java.util.Random;

public class BusinessCharacteristics implements BusinessProcessInterface{
    private double[]durationMap;// Создаем одномерный массив для хранения значений длительности элементарных операций.
    private double planningHorizon;// Создается переменная для определения горизонта планирования.
    private int numberOfSubjects;// Переменная для определения численности персонала.
    private double budgetWorkTime;// Создается переменная для определения бюджета рабочего времени.
    private double realWorkLoad;// Cоздается переменная для определения продолжительности поиска альтернатив по закону Хика-Хаймана.
    private int globalNumberOfOperation;// Создается переменная для определения общей численности элементарных операций.
    private int numberOfOperation;// Создается переменная для определения средней численности элементарных операций.

    public BusinessCharacteristics(int numberOfSubjects, double planningHorizon, int numberOfOperation) {
        this.planningHorizon = planningHorizon;
        this.numberOfOperation = numberOfOperation;
        this.globalNumberOfOperation = numberOfSubjects * numberOfOperation;
        this.budgetWorkTime = planningHorizon * this.globalNumberOfOperation;
        setDurationOfOperations(this.budgetWorkTime);
    }

    public void BusinessCharacteristics(){
     }

    public int getNumberOfSubjects() {
        return numberOfSubjects;
    }

    public void setNumberOfSubjects(int numberOfSubjects) {
        this.numberOfSubjects = numberOfSubjects;
    }

    Random rand = new Random();
    @Override
    public void setDirectWorkLoad(double budgetWorkTime) {
        this.budgetWorkTime = budgetWorkTime;
    }

    @Override
    public double getDirectWorkLoad() {
        return this.budgetWorkTime;
    }

    @Override
    public void setRealWorkLoad(int numberOfOperation) {
    this.numberOfOperation = numberOfOperation;
        realWorkLoad = Math.log(numberOfOperation+1) / Math.log(2);// Вычисление логарифма по основанию 2.
    }

    @Override
    public double getRealWorkLoad() {
        return this.realWorkLoad;
    }

    @Override
    public void setPlanningHorizon(double planningHorizon) {
    this.planningHorizon = planningHorizon;
    }

    @Override
    public double getPlanningHorizon() {
        return this.planningHorizon;
    }

    @Override
    public void setNumberOfOperation(int numberOfOperation) {
    this.numberOfOperation = numberOfOperation;
    }

    @Override
    public int getNumberOfOperation() {
        return this.numberOfOperation;
    }

    @Override
    public void setDurationOfOperations(double budgetWorkTime) {
        double duration = budgetWorkTime;
        for(int i = 0; i < globalNumberOfOperation; i++){
            if(i == globalNumberOfOperation - 1){
                durationMap[i] = duration;
            }else{
                durationMap[i] = rand.nextDouble(duration);
                duration = duration - durationMap[i];
            }
        }
    }

    @Override
    public double getDurationOfOperation(int id) {
        return durationMap[id];
    }

    @Override
    public double[] getDurationMap() {
        return durationMap;
    }
}
