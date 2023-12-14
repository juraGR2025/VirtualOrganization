package ru.virtual.experiment.virtualorganization;

import java.util.Map;

public class WorkLoadController {
    private int inerationNumb = 100000;// Устанавливаем количество итераций для данных виртуального эксперимента.
    private double[]durationMap;// Создаем одномерный массив для хранения значений длительности элементарных операций.
    private double planningHorizon;// Создается переменная для определения горизонта планирования.
    private int numberOfSubjects;// Переменная для определения численности персонала.
    private int numberOfOperation;// Переменная для определения количества элементарных операций технологического процесса (среднее значение на одного сотрудника).
    Map<Integer, Double> doubleMap;// Создаем hash map для хранения пар id сотрудника - уровень его загруженности.
}
