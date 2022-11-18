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
import java.time.LocalDate;
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
    private TableColumn<?, ?> departtimecol;

    @FXML
    private TableColumn<?, ?> arrivaltimecol;

    @FXML
    private TextField airportinput;

    @FXML
    private DatePicker arrivalinput;
    @FXML
    private TextField arrivaltempsinput;
    @FXML
    private TextField destinationinput;

    @FXML
    private TextField departtempsinput;
    @FXML
    private DatePicker departinput;
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
            String depart;
            String arrival;
            String destination;
            String departtime;
            String arrivaltime;
            int airport;
            int airplane;

            depart = String.valueOf(tab.getSelectionModel().getSelectedItem().getDateDepart());
            arrival = String.valueOf(tab.getSelectionModel().getSelectedItem().getDateArrival());
            departtime = tab.getSelectionModel().getSelectedItem().getTempsDepart();
            arrivaltime = tab.getSelectionModel().getSelectedItem().getTempsArrival();
            destination = tab.getSelectionModel().getSelectedItem().getDestination();
            airport = tab.getSelectionModel().getSelectedItem().getIdAirport();
            airplane = tab.getSelectionModel().getSelectedItem().getIdAirplane();

            departinput.setValue(LocalDate.parse(depart));
            arrivalinput.setValue(LocalDate.parse(arrival));
            destinationinput.setText(String.valueOf(destination));
            departtempsinput.setText(departtime);
            arrivaltempsinput.setText(arrivaltime);
            combo1.setValue(airport);
            combo2.setValue(airplane);
        }
        @FXML
        void onAdd(ActionEvent event) throws SQLException {
            if (this.isInputValid()) {
                String depart = String.valueOf(departinput.getValue());
                String arrival = String.valueOf(arrivalinput.getValue());
                String arrivaltime = arrivaltempsinput.getText();
                String departtime = departtempsinput.getText();
                String destination = destinationinput.getText();
                int airport = combo1.getValue();
                int airplane = combo2.getValue();

                PreparedStatement x = (PreparedStatement) conn.prepareStatement("insert into Flight(datedepart,datearrival,tempsdepart,tempsarrival,destination,idAirport,idAirplane) values(?,?,?,?,?,?,?)");

                x.setString(1, depart);
                x.setString(2, arrival);
                x.setString(3, departtime);
                x.setString(4, arrivaltime);
                x.setString(5, destination);
                x.setInt(6, airport);
                x.setInt(7, airplane);
                x.execute();

                Flight c = new Flight(java.sql.Date.valueOf(this.departinput.getValue()), java.sql.Date.valueOf(this.arrivalinput.getValue()),this.departtempsinput.getText(),this.arrivaltempsinput.getText(), this.destinationinput.getText(),this.combo1.getValue(),this.combo2.getValue());
                tab.getItems().add(c);
                nbA.setText(String.valueOf(list.size()));
                this.clearInput();
            }



        }
        private void clearInput() {
            this.departinput.setValue(null);
            this.arrivalinput.setValue(null);
            this.departtempsinput.setText("");
            this.arrivaltempsinput.setText("");
            this.destinationinput.setText("");
            this.combo1.setValue(null);
            this.combo2.setValue(null);
        }

        private boolean isInputValid() {
            if (this.departinput.getValue()==null || this.arrivalinput.getValue()==null || this.departtempsinput.getText().isEmpty()|| this.arrivaltempsinput.getText().isEmpty()|| this.destinationinput.getText().isEmpty()|| this.combo1.getValue()==null || this.combo2.getValue()==null) {
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
                String departtime;
                String arrivaltime;
                String destination;
                int airport;
                int airplane;

                int idA;
                idA = tab.getSelectionModel().getSelectedItem().getIdF();
                depart = String.valueOf(departinput.getValue());
                arrival = String.valueOf(arrivalinput.getValue());
                departtime = departtempsinput.getText();
                arrivaltime = arrivaltempsinput.getText();
                destination = destinationinput.getText();
                airport=combo1.getValue();
                airplane=combo2.getValue();
                PreparedStatement x = (PreparedStatement) conn.prepareStatement("update flight set datedepart='" + depart + "',datearrival='" + arrival + "',tempsdepart='"+departtime+"',tempsarrival='"+arrivaltime+"', destination='" + destination + "',idAirport='"+airport+"',idAirplane='"+airplane+"' where idF='" + idA + "' ");
                x.execute();
                clearInput();
                list.clear();
                ResultSet rs = conn.createStatement().executeQuery("select * from flight");
                while (rs.next()) {
                    list.add(new Flight(rs.getInt("idF"),rs.getDate("dateDepart"), rs.getDate("dateArrival"), rs.getString("tempsDepart"), rs.getString("tempsArrival"), rs.getString("destination"),rs.getInt("idAirport"),rs.getInt("idAirplane")));
                }
                datedepartcol.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
                datearrivalcol.setCellValueFactory(new PropertyValueFactory<>("dateArrival"));
                departtimecol.setCellValueFactory(new PropertyValueFactory<>("tempsDepart"));
                arrivaltimecol.setCellValueFactory(new PropertyValueFactory<>("tempsArrival"));
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
                    list.add(new Flight(rs.getInt("idF"),rs.getDate("dateDepart"), rs.getDate("dateArrival"), rs.getString("tempsDepart"),rs.getString("tempsArrival"), rs.getString("destination"),rs.getInt("idAirport"),rs.getInt("idAirplane")));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            datedepartcol.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
            datearrivalcol.setCellValueFactory(new PropertyValueFactory<>("dateArrival"));
            departtimecol.setCellValueFactory(new PropertyValueFactory<>("tempsDepart"));
            arrivaltimecol.setCellValueFactory(new PropertyValueFactory<>("tempsArrival"));
            destinationcol.setCellValueFactory(new PropertyValueFactory<>("destination"));
            airportcol.setCellValueFactory(new PropertyValueFactory<>("idAirport"));
            airplanecol.setCellValueFactory(new PropertyValueFactory<>("idAirplane"));
            tab.setItems(list);
            nbA.setText(String.valueOf(list.size()));
        }
    }
