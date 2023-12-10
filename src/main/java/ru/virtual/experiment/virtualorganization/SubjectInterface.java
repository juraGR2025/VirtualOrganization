package ru.virtual.experiment.virtualorganization;

public interface SubjectInterface {
    int NumberOfSubjects = 0;
    int NumberOfOperation = 0;
    void setNumberOfSubjects(int NumberOfSubjects);// Метод для установки численности субъекта.
    int getNumberOfSubjects();// Метод для выдачи численности субъекта.
    void setKompetence(int NumberOfSubjects, int NumberOfOperation);// Метод для установки показателей компетенций персоналу.
    double getMaxKompetence(int id);// Метод для поиска максимальной компетенции по номеру операции.
    double getMinKompetence(int id);// Метод для поиска минимальной компетенции по номеру операции.
    double employeeWorkloadTime(double directWorkTime, double employeeKompetence);// Метод, который возвращает время загруженности одного сотрудника.
    double getLoadFactor(int idEmployee, double PlanningHorizon);// Метод, который возвращает коэффициент загруженности одного сотрудника.
}
