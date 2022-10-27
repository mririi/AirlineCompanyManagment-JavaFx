package com.example.airlinecompanymanagment.controllers;

import com.example.airlinecompanymanagment.models.Airport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AirportController {
    Connection conn = null;

    public AirportController() {
        conn = SingletonConnection.getConnection();
    }

    @FXML
    private Button importbtn;

    @FXML
    private TableColumn<Airport, String> citycol;

    @FXML
    private TableColumn<Airport, String> namecol;

    @FXML
    private TableColumn<Airport, String> statecol;

    @FXML
    private TableView<Airport> tab;
    @FXML
    private Button closebtn;
    ObservableList<Airport> list = FXCollections.observableArrayList();

    @FXML
    void OnSet(MouseEvent event) {
        String n;
        String p;
        String a;

        n = tab.getSelectionModel().getSelectedItem().getName();
        p = tab.getSelectionModel().getSelectedItem().getCity();
        a = tab.getSelectionModel().getSelectedItem().getState();

        namecol.setText(String.valueOf(n));
        citycol.setText(String.valueOf(p));
        statecol.setText(String.valueOf(a));


    }

    @FXML
    void onImport(ActionEvent event) throws SQLException {
        list.clear();
        ResultSet rs = conn.createStatement().executeQuery("select * from Airport");
        while (rs.next()) {
            list.add(new Airport(rs.getInt("idA"), rs.getString("name"), rs.getString("city"), rs.getString("state")));
        }
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        citycol.setCellValueFactory(new PropertyValueFactory<>("city"));
        statecol.setCellValueFactory(new PropertyValueFactory<>("state"));
        tab.setItems(list);
    }
}