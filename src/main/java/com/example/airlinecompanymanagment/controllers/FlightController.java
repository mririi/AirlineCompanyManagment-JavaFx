package com.example.airlinecompanymanagment.controllers;

import com.example.airlinecompanymanagment.models.Airport;
import com.example.airlinecompanymanagment.models.Flight;
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
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Integer.parseInt;

public class FlightController implements Initializable{


        Connection conn = null;

        public FlightController() {
            conn = SingletonConnection.getConnection();
        }

    @FXML
    private Button addbtn;

    @FXML
    private TableColumn<?, ?> airplanecol;

    @FXML
    private TableColumn<?, ?> airportcol;

    @FXML
    private TextField airportinput;

    @FXML
    private TextField arrivalinput;
    @FXML
    private TextField destinationinput;

    @FXML
    private TextField departinput;
    @FXML
    private ComboBox<Integer> combo1;
    @FXML
    private ComboBox<Integer> combo2;
    @FXML
    private TextField airplaneinput;
    @FXML
    private TableColumn<Flight, String> datearrivalcol;

    @FXML
    private TableColumn<Flight, String> datedepartcol;

    @FXML
    private Button deletebtn;

    @FXML
    private TableColumn<Flight, String> destinationcol;

    @FXML
    private Button modifybtn;

    @FXML
    private Text nbA;

    @FXML
    private TableView<Flight> tab;
        ObservableList<Flight> list = FXCollections.observableArrayList();

        @FXML
        void OnSet(MouseEvent event) {
            Date depart;
            Date arrival;
            String destination;
            int airport;
            int airplane;

            depart = tab.getSelectionModel().getSelectedItem().getDateDepart();
            arrival = tab.getSelectionModel().getSelectedItem().getDateArrival();
            destination = tab.getSelectionModel().getSelectedItem().getDestination();
            airport = tab.getSelectionModel().getSelectedItem().getIdAirport();
            airplane = tab.getSelectionModel().getSelectedItem().getIdAirplane();

            departinput.setText(String.valueOf(depart));
            arrivalinput.setText(String.valueOf(arrival));
            destinationinput.setText(String.valueOf(destination));
            combo1.setValue(airport);
            combo2.setValue(airplane);


        }
        @FXML
        void onAdd(ActionEvent event) throws SQLException {
            if (this.isInputValid()) {
                String depart = departinput.getText();
                String arrival = arrivalinput.getText();
                String destination = destinationinput.getText();
                int airport = combo1.getValue();
                int airplane = combo2.getValue();

                PreparedStatement x = (PreparedStatement) conn.prepareStatement("insert into Flight(datedepart,datearrival,destination,idAirport,idAirplane) values(?,?,?,?,?)");

                x.setString(1, depart);
                x.setString(2, arrival);
                x.setString(3, destination);
                x.setInt(4, airport);
                x.setInt(5, airplane);
                x.execute();

                Flight c = new Flight(java.sql.Date.valueOf(this.departinput.getText()), java.sql.Date.valueOf(this.arrivalinput.getText()), this.destinationinput.getText(),this.combo1.getValue(),this.combo2.getValue());
                tab.getItems().add(c);
                nbA.setText(String.valueOf(list.size()));
                this.clearInput();
            }



        }
        private void clearInput() {
            this.departinput.setText("");
            this.arrivalinput.setText("");
            this.destinationinput.setText("");
            this.combo1.setValue(null);
            this.combo2.setValue(null);
        }

        private boolean isInputValid() {
            if (this.departinput.getText().isEmpty() || this.arrivalinput.getText().isEmpty() || this.destinationinput.getText().isEmpty()|| this.combo1.getValue()==null || this.combo2.getValue()==null) {
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
                idA = tab.getSelectionModel().getSelectedItem().getIdF();
                tab.getItems().remove(tab.getSelectionModel().getSelectedItem());
                PreparedStatement x = (PreparedStatement) conn.prepareStatement("delete from flight where idF=" + idA);
                x.execute();
                nbA.setText(String.valueOf(list.size()));
            }}
        @FXML
        void onModify(ActionEvent event) throws SQLException {
            if (this.isInputValid()) {
                String depart;
                String arrival;
                String destination;
                int airport;
                int airplane;

                int idA;
                idA = tab.getSelectionModel().getSelectedItem().getIdF();
                depart = departinput.getText();
                arrival = arrivalinput.getText();
                destination = destinationinput.getText();
                airport=combo1.getValue();
                airplane=combo2.getValue();
                PreparedStatement x = (PreparedStatement) conn.prepareStatement("update flight set datedepart='" + depart + "',datearrival='" + arrival + "', destination='" + destination + "',idAirport='"+airport+"',idAirplane='"+airplane+"' where idF='" + idA + "' ");
                x.execute();
                clearInput();
                list.clear();
                ResultSet rs = conn.createStatement().executeQuery("select * from flight");
                while (rs.next()) {
                    list.add(new Flight(rs.getInt("idF"),rs.getDate("dateDepart"), rs.getDate("dateArrival"), rs.getString("destination"),rs.getInt("idAirport"),rs.getInt("idAirplane")));
                }
                datedepartcol.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
                datearrivalcol.setCellValueFactory(new PropertyValueFactory<>("dateArrival"));
                destinationcol.setCellValueFactory(new PropertyValueFactory<>("destination"));
                airportcol.setCellValueFactory(new PropertyValueFactory<>("idAirport"));
                airplanecol.setCellValueFactory(new PropertyValueFactory<>("idAirplane"));
                tab.setItems(list);
            }
        }
    @FXML
    List<Integer> onidairport () throws SQLException {

        List<Integer> options = new ArrayList<>();

        String query = "Select idA from airport";
        PreparedStatement st = (PreparedStatement) conn.prepareStatement(query);
        ResultSet rs = (ResultSet) st.executeQuery();
        while (rs.next()) {
            options.add(rs.getInt("idA"));
        }
        return options;
    }
    @FXML
    List<Integer> onidairplane () throws SQLException {

        List<Integer> options = new ArrayList<>();

        String query = "Select idAirplane from airplane";
        PreparedStatement st = (PreparedStatement) conn.prepareStatement(query);
        ResultSet rs = (ResultSet) st.executeQuery();
        while (rs.next()) {
            options.add(rs.getInt("idAirplane"));
        }
        return options;
    }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            try {
                combo1.setItems(FXCollections.observableArrayList(onidairport()));
                combo2.setItems(FXCollections.observableArrayList(onidairplane()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            list.clear();
            ResultSet rs = null;
            try {
                rs = conn.createStatement().executeQuery("select * from Flight");
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
                    list.add(new Flight(rs.getInt("idF"),rs.getDate("dateDepart"), rs.getDate("dateArrival"), rs.getString("destination"),rs.getInt("idAirport"),rs.getInt("idAirplane")));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            datedepartcol.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
            datearrivalcol.setCellValueFactory(new PropertyValueFactory<>("dateArrival"));
            destinationcol.setCellValueFactory(new PropertyValueFactory<>("destination"));
            airportcol.setCellValueFactory(new PropertyValueFactory<>("idAirport"));
            airplanecol.setCellValueFactory(new PropertyValueFactory<>("idAirplane"));
            tab.setItems(list);
            nbA.setText(String.valueOf(list.size()));
        }
    }
