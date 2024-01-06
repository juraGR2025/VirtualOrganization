package ru.virtual.experiment.virtualorganization.models;

public class Employee {
    double realEmployeeWorkloadTime;// Реальное время загруженности сотрудника.
    double loadFactor;// Коэффициент загруженности сотрудника.

    public double getRealEmployeeWorkloadTime() {
        return realEmployeeWorkloadTime;
    }

    public void setRealEmployeeWorkloadTime(double realEmployeeWorkloadTime) {
        this.realEmployeeWorkloadTime = realEmployeeWorkloadTime;
    }

    public double getLoadFactor() {
        return loadFactor;
    }

    public void setLoadFactor(double loadFactor) {
        this.loadFactor = loadFactor;
    }



}
