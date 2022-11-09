package com.example.airlinecompanymanagment;

import com.example.airlinecompanymanagment.controllers.SingletonConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class OverviewController implements Initializable {
    Connection conn = null;

    public OverviewController() {
        conn = SingletonConnection.getConnection();
    }
    @FXML
    private Text nbAirplanes;

    @FXML
    private Text nbAirports;

    @FXML
    private Text nbClients;

    @FXML
    private Text nbEmployees;

    @FXML
    private Text nbFlights;

    @FXML
    private Text nbIFlights;

    @FXML
    private Text nbDepartments;

    @FXML
    private Text nbTickets;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String queryAirplane = "Select count(*) as counts from Airplane";
        String queryAirport = "Select count(*) as counts from Airport";
        String queryTicket = "Select count(*) as counts from Ticket";
        String queryClient = "Select count(*) as counts from Client";
        String queryFlight = "Select count(*) as counts from Flight";
        String queryEmployee = "Select count(*) as counts from Employee";
        String queryDepartment = "Select count(*) as counts from Department";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        String queryIFlights = "Select count(*) as counts from Flight where datedepart>'"+now+"'";
        try {
            PreparedStatement st1 = (PreparedStatement) conn.prepareStatement(queryAirplane);
            PreparedStatement st2 = (PreparedStatement) conn.prepareStatement(queryAirport);
            PreparedStatement st3 = (PreparedStatement) conn.prepareStatement(queryTicket);
            PreparedStatement st4 = (PreparedStatement) conn.prepareStatement(queryClient);
            PreparedStatement st5 = (PreparedStatement) conn.prepareStatement(queryFlight);
            PreparedStatement st6 = (PreparedStatement) conn.prepareStatement(queryEmployee);
            PreparedStatement st7 = (PreparedStatement) conn.prepareStatement(queryDepartment);
            PreparedStatement st8 = (PreparedStatement) conn.prepareStatement(queryIFlights);
            ResultSet x1=st1.executeQuery();
            ResultSet x2=st2.executeQuery();
            ResultSet x3=st3.executeQuery();
            ResultSet x4=st4.executeQuery();
            ResultSet x5=st5.executeQuery();
            ResultSet x6=st6.executeQuery();
            ResultSet x7=st7.executeQuery();
            ResultSet x8=st8.executeQuery();
            while (x1.next()&&x2.next()&&x3.next()&&x4.next()&&x5.next()&&x6.next()&&x7.next()&&x8.next()) {
                nbAirplanes.setText(String.valueOf(x1.getInt("counts")));
                nbAirports.setText(String.valueOf(x2.getInt("counts")));
                nbTickets.setText(String.valueOf(x3.getInt("counts")));
                nbClients.setText(String.valueOf(x4.getInt("counts")));
                nbFlights.setText(String.valueOf(x5.getInt("counts")));
                nbEmployees.setText(String.valueOf(x6.getInt("counts")));
                nbDepartments.setText(String.valueOf(x7.getInt("counts")));
                nbIFlights.setText(String.valueOf(x8.getInt("counts")));
                System.out.println(x8.getInt("counts"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
