package com.example.airlinecompanymanagment.models;

import java.sql.Date;

public class Flight {
    private int idF;
    private Date dateDepart;
    private Date dateArrival;
    private String tempsDepart;
    private String tempsArrival;
    private String destination;
    private String idAirport;
    private String idAirplane;

    public Flight(int idF, Date dateDepart, Date dateArrival, String tempsDepart, String tempsArrival, String destination, String idAirport, String idAirplane) {
        this.idF = idF;
        this.dateDepart = dateDepart;
        this.dateArrival = dateArrival;
        this.tempsDepart = tempsDepart;
        this.tempsArrival = tempsArrival;
        this.destination = destination;
        this.idAirport = idAirport;
        this.idAirplane = idAirplane;
    }

    public Flight(Date dateDepart, Date dateArrival, String tempsDepart, String tempsArrival, String destination, String idAirport, String idAirplane) {
        this.dateDepart = dateDepart;
        this.dateArrival = dateArrival;
        this.tempsDepart = tempsDepart;
        this.tempsArrival = tempsArrival;
        this.destination = destination;
        this.idAirport = idAirport;
        this.idAirplane = idAirplane;
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

    public String getTempsDepart() {
        return tempsDepart;
    }

    public void setTempsDepart(String tempsDepart) {
        this.tempsDepart = tempsDepart;
    }

    public String getTempsArrival() {
        return tempsArrival;
    }

    public void setTempsArrival(String tempsArrival) {
        this.tempsArrival = tempsArrival;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getIdAirport() {
        return idAirport;
    }

    public void setIdAirport(String idAirport) {
        this.idAirport = idAirport;
    }

    public String getIdAirplane() {
        return idAirplane;
    }

    public void setIdAirplane(String idAirplane) {
        this.idAirplane = idAirplane;
    }
}
