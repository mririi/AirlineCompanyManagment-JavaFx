package com.example.airlinecompanymanagment.models;

public class Employees {
    private int idE;
    private double salary;
    private int idD; //ID DEPARTMENT

    public Employees(int idE, double salary, int idD) {
        this.idE = idE;
        this.salary = salary;
        this.idD = idD;
    }

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getIdD() {
        return idD;
    }

    public void setIdD(int idD) {
        this.idD = idD;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "idE=" + idE +
                ", salary=" + salary +
                ", idD=" + idD +
                '}';
    }
}
