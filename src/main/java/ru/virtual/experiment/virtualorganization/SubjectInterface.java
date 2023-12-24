package ru.virtual.experiment.virtualorganization;

public interface SubjectInterface {
    int NumberOfSubjects = 0;
    int NumberOfOperation = 0;
    void setNumberOfSubjects(int NumberOfSubjects);// Метод для установки численности субъекта.
    int getNumberOfSubjects();// Метод для выдачи численности субъекта.
    void setKompetence(int NumberOfSubjects, int NumberOfOperation);// Метод для установки показателей компетенций персоналу.
    int getIdMaxKompetence(int id);// Метод для поиска сотрудника с максимальной компетенцией по номеру операции.
    double getEmployeeKompetence(int idEmployee, int idOperation);// Метод для поиска компетенции по id сотрудника и id номера операции.
    double getMinKompetence(int id);// Метод для поиска минимальной компетенции по номеру операции.
    double employeeWorkloadTime(double directWorkTime, double PlanningHorizon, double employeeKompetence, int idOperation, int idEmployee);// Метод, который возвращает время загруженности одного сотрудника.
    double getLoadFactor(int idEmployee, double PlanningHorizon);// Метод, который возвращает коэффициент загруженности одного сотрудника.
}
