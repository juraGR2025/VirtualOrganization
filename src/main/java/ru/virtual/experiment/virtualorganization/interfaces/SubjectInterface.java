package ru.virtual.experiment.virtualorganization.interfaces;

public interface SubjectInterface {
    void setNumberOfSubjects(int numberOfSubjects);// Метод для установки численности субъекта.
    int getNumberOfSubjects();// Метод для выдачи численности субъекта.
    void setKompetence(int numberOfSubjects, int numberOfOperation);// Метод для установки показателей компетенций персоналу.
    int getIdMaxKompetence(int id);// Метод для поиска сотрудника с максимальной компетенцией по номеру операции.
    double getEmployeeKompetence(int idEmployee, int idOperation);// Метод для поиска компетенции по id сотрудника и id номера операции.
    double getMinKompetence(int id);// Метод для поиска минимальной компетенции по номеру операции.
    double employeeWorkloadTime(double directWorkTime, double PlanningHorizon, double employeeKompetence, int idOperation, int idEmployee);// Метод, который возвращает время загруженности одного сотрудника.
    double getLoadFactor(int idEmployee);// Метод, который возвращает коэффициент загруженности одного сотрудника.
}
