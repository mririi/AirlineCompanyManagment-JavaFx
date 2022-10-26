package com.example.airlinecompanymanagment.controllers;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonController {
    Connection conn = null;
    public PersonController() {
        conn = SingletonConnection.getConnection();
    }
    void onImport(ActionEvent event) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("select * from Personne");
        System.out.println(rs);
    }
}

