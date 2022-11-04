package com.example.airlinecompanymanagment.controllers;

import com.example.airlinecompanymanagment.models.Airport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AirportController implements Initializable {
    Connection conn = null;

    public AirportController() {
        conn = SingletonConnection.getConnection();
    }

    @FXML
    private TextField cityInput;
    @FXML
    private Text nbA;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField stateInput;

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

        nameInput.setText(String.valueOf(n));
        cityInput.setText(String.valueOf(p));
        stateInput.setText(String.valueOf(a));


    }
    @FXML
    void onAdd(ActionEvent event) throws SQLException {
        if (this.isInputValid()) {
            String name = nameInput.getText();
            String city = cityInput.getText();
            String state = stateInput.getText();

            PreparedStatement x = (PreparedStatement) conn.prepareStatement("insert into Airport(name,city,state) values(?,?,?)");

            x.setString(1, name);
            x.setString(2, city);
            x.setString(3, state);
            x.execute();

            Airport c = new Airport(this.nameInput.getText(),this.cityInput.getText(), this.stateInput.getText());
            tab.getItems().add(c);
            nbA.setText(String.valueOf(list.size()));
            this.clearInput();
        }



    }
    private void clearInput() {
        this.nameInput.setText("");
        this.cityInput.setText("");
        this.stateInput.setText("");
    }

    private boolean isInputValid() {
        if (this.nameInput.getText().isEmpty() || this.cityInput.getText().isEmpty() || this.stateInput.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input error!");
            alert.setHeaderText(null);
            alert.setContentText("Les champs ne doivent pas etre vide!");
            alert.show();
            return false;
        }
        return true;
    }
    @FXML
    void onDelete(ActionEvent event) throws SQLException {
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure to delete ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get()==ButtonType.OK) {
            int idA;
            idA = tab.getSelectionModel().getSelectedItem().getIdA();
            tab.getItems().remove(tab.getSelectionModel().getSelectedItem());
            PreparedStatement x = (PreparedStatement) conn.prepareStatement("delete from airport where idA=" + idA);
            x.execute();
            nbA.setText(String.valueOf(list.size()));
        }}
        @FXML
        void onModify(ActionEvent event) throws SQLException {
            if (this.isInputValid()) {
                String n;
                String c;
                String s;
                int idA;
                idA = tab.getSelectionModel().getSelectedItem().getIdA();
                n = nameInput.getText();
                c = cityInput.getText();
                s = stateInput.getText();
                PreparedStatement x = (PreparedStatement) conn.prepareStatement("update airport set name='" + n + "',city='" + c + "', state='" + s + "' where idA='" + idA + "' ");
                x.execute();
                clearInput();
                list.clear();
                ResultSet rs = conn.createStatement().executeQuery("select * from airport");
                while (rs.next()) {
                    list.add(new Airport(rs.getInt("idA"),rs.getString("name"), rs.getString("city"), rs.getString("state")));
                }
                namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
                citycol.setCellValueFactory(new PropertyValueFactory<>("city"));
                statecol.setCellValueFactory(new PropertyValueFactory<>("state"));
                tab.setItems(list);
            }
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.clear();
        ResultSet rs = null;
        try {
            rs = conn.createStatement().executeQuery("select * from Airport");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                list.add(new Airport(rs.getInt("idA"),rs.getString("name"), rs.getString("city"), rs.getString("state")));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        citycol.setCellValueFactory(new PropertyValueFactory<>("city"));
        statecol.setCellValueFactory(new PropertyValueFactory<>("state"));
        tab.setItems(list);
        nbA.setText(String.valueOf(list.size()));
    }
}