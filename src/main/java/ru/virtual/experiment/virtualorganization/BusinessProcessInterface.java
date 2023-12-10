package ru.virtual.experiment.virtualorganization;

public interface BusinessProcessInterface {
    void setDirectWorkLoad(double budgetWorkTime);// Метод определяет бюджет рабочего времени, заданный директивно.
    double getDirectWorkLoad();// Метод возвращает бюджет рабочего времени, заданный директивно.
    void setRealWorkLoad(int numberOfOperation);// Метод определяет бюджет рабочего времени, расчитанный с учетом закона Хика-Хаймана.
    double getRealWorkLoad();// Метод возвращает бюджет рабочего времени, расчитанный с учетом показателей компетентности.
    void setPlanningHorizon(double planningHorizon);// Метод устанавливает горизонт планирования для деловых процессов организации.
    double getPlanningHorizon();// Метод возвращает значение горизонта планирования для деловых процессов организации.
    void setNumberOfOperation(int numberOfOperation);// Метод устанавливает количество элементарных операций.
    int getNumberOfOperation();// Метод возвращает количество элементарных операций.
    void setDurationOfOperations();// Метод устанавливает продолжительность элементарной операции.
    double getDurationOfOperation(int id);// Метод возвращает продолжительность элементарной операции по ее id.
    double[] getDurationMap();// Метод возвращает массив продолжительности каждой элементарной операции.

}
