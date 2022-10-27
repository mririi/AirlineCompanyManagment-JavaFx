package com.example.airlinecompanymanagment.models;

import java.sql.Date;

public class Flight {
    private int idF;
    private Date dateDepart;
    private Date dateArrival;
    private String destination;
    private int idA; //ID AEROPORT

    public Flight(int idF, Date dateDepart, Date dateArrival, String destination, int idA) {
        this.idF = idF;
        this.dateDepart = dateDepart;
        this.dateArrival = dateArrival;
        this.destination = destination;
        this.idA = idA;
    }

    public int getIdF() {
        return idF;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(Date dateArrival) {
        this.dateArrival = dateArrival;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "idF=" + idF +
                ", dateDepart=" + dateDepart +
                ", dateArrival=" + dateArrival +
                ", destination='" + destination + '\'' +
                ", idA=" + idA +
                '}';
    }
}
