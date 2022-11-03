package com.example.airlinecompanymanagment.models;

public class Airplane {
    private int idAirplane;
    private String name;
    private int status;

    public Airplane(int idAirplane, String name, int status) {
        this.idAirplane = idAirplane;
        this.name = name;
        this.status = status;
    }
    public Airplane(String name, int status) {
        this.name = name;
        this.status = status;
    }
    public int getIdAirplane() {
        return idAirplane;
    }

    public void setIdAirplane(int idAirplane) {
        this.idAirplane = idAirplane;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
