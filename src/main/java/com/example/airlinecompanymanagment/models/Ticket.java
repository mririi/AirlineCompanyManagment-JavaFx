package com.example.airlinecompanymanagment.models;

public class Ticket {
    private int idT;
    private double price;
    private int idF; //ID FLIGHT
    private String idC;//ID CLIENT

    public Ticket(int idT, double price, String idC, int idF) {
        this.idT = idT;
        this.price = price;
        this.idF = idF;
        this.idC = idC;
    }
    public Ticket( double price,  String idC,int idF) {
        this.price = price;
        this.idF = idF;
        this.idC = idC;
    }

    public int getIdT() {
        return idT;
    }

    public void setIdT(int idT) {
        this.idT = idT;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIdF() {
        return idF;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    public String getIdC() {
        return idC;
    }

    public void setIdC(String idC) {
        this.idC = idC;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idT=" + idT +
                ", price=" + price +
                ", idF=" + idF +
                ", idC=" + idC +
                '}';
    }
}
