package com.example.airlinecompanymanagment.models;

import java.util.Date;

public class Person {

    private int idP;
    private String lastName;
    private String firstName;
    private String address;
    private int tel;
    private String email;
    private Date birthDate;
    private int idDep;
    private int salaire;


    public Person(int idP, String lastName, String firstName, String address, int tel, String email, Date birthDate) {
        this.idP = idP;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Person(int idP, String lastName, String firstName, String address, int tel, String email, Date birthDate, int idDep, int salaire) {
        this.idP = idP;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.birthDate = birthDate;
        this.idDep = idDep;
        this.salaire = salaire;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getIdDep() {
        return idDep;
    }

    public void setIdDep(int idDep) {
        this.idDep = idDep;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }
}
