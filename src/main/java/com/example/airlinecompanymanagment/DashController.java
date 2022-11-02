package com.example.airlinecompanymanagment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashController {


    @FXML
    private Button airportbtn;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button clientbtn;

    @FXML
    private Button departmentbtn;

    @FXML
    private Button employeesbtn;

    @FXML
    private Button flightbtn;

    @FXML
    private Button homebtn;

    @FXML
    private VBox pagecenter;

    @FXML
    private VBox sidebar;

    @FXML
    private Button ticketbtn;

    @FXML
    private void loadFXML(String fileName) {
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource(fileName+".fxml"));
            borderPane.setCenter(parent);
        } catch (IOException ex) {
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   @FXML
    void loadAirplaneView(ActionEvent event) {
        loadFXML("Airplane");
    }
    @FXML
    void loadAirportView(ActionEvent event) {
        loadFXML("Airport");
    }
    @FXML
    void loadClientView(ActionEvent event) {
        loadFXML("Client");
    }

    @FXML
    void loadDepartmentView(ActionEvent event) {
        loadFXML("Department");
    }

    @FXML
    void loadEmployeesView(ActionEvent event) {
        loadFXML("Employees");
    }

    @FXML
    void loadFlightView(ActionEvent event) {
        loadFXML("Flight");
    }

    @FXML
    void loadTicketView(ActionEvent event) {
        loadFXML("Ticket");
    }

    public void onClose(ActionEvent actionEvent) {
        System.exit(0);
    }
}
