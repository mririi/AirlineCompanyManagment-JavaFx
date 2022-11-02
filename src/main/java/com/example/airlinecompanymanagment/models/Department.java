package com.example.airlinecompanymanagment.models;

public class Department {
    private int idD;
    private String name;

    public Department(int idD, String name) {
        this.idD = idD;
        this.name = name;
    }

    public Department(String name) {
        this.name = name;
    }

    public int getIdD() {
        return idD;
    }

    public void setIdD(int idD) {
        this.idD = idD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
