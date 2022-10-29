package com.example.airlinecompanymanagment.models;

public class Airport {
    private int idA;
    private String name;
    private String city;
    private String state;

    public Airport(int idA, String name, String city, String state) {
        this.idA = idA;
        this.name = name;
        this.city = city;
        this.state = state;
    }
    public Airport(String name, String city, String state) {
        this.name = name;
        this.city = city;
        this.state = state;
    }
    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Aeroport{" +
                "idA=" + idA +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
