package com.example.airlinecompanymanagment.controllers;

import com.example.airlinecompanymanagment.models.Airport;
import com.example.airlinecompanymanagment.models.Flight;
import com.example.airlinecompanymanagment.models.Ticket;
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

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class TicketsController implements Initializable {
    Connection conn = null;

    public TicketsController() {
        conn = SingletonConnection.getConnection();
    }
    @FXML
    private Button addbtn;

    @FXML
    private TextField clientInput;

    @FXML
    private TableColumn<Ticket, String> clientcol;

    @FXML
    private Button deletebtn;

    @FXML
    private TextField flightInput;

    @FXML
    private TableColumn<Ticket, String> flightcol;

    @FXML
    private Button modifybtn;

    @FXML
    private Text nbA;

    @FXML
    private TextField priceInput;

    @FXML
    private TableColumn<Ticket, String> pricecol;

    @FXML
    private TableView<Ticket> tab;
    ObservableList<Ticket> list = FXCollections.observableArrayList();
    @FXML
    void OnSet(MouseEvent event) {
        double p;
        int f;
        int c;

        p = tab.getSelectionModel().getSelectedItem().getPrice();
        f = tab.getSelectionModel().getSelectedItem().getIdF();
        c = tab.getSelectionModel().getSelectedItem().getIdC();

        priceInput.setText(String.valueOf(p));
        flightInput.setText(String.valueOf(f));
        clientInput.setText(String.valueOf(c));


    }
    @FXML
    void onAdd(ActionEvent event) throws SQLException {
        if (this.isInputValid()) {
            String price = priceInput.getText();
            String flight = flightInput.getText();
            String client = clientInput.getText();

            PreparedStatement x = (PreparedStatement) conn.prepareStatement("insert into Ticket(price,idC,idF) values(?,?,?)");

            x.setString(1, price);
            x.setString(2, flight);
            x.setString(3, client);
            x.execute();

            Ticket c = new Ticket(parseDouble(this.priceInput.getText()),parseInt(this.flightInput.getText()), parseInt(this.clientInput.getText()));
            tab.getItems().add(c);
            this.clearInput();
        }



    }
    private void clearInput() {
        this.priceInput.setText("");
        this.clientInput.setText("");
        this.flightInput.setText("");
    }

    private boolean isInputValid() {
        if (this.priceInput.getText().isEmpty() || this.clientInput.getText().isEmpty() || this.flightInput.getText().isEmpty()) {
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
            idA = tab.getSelectionModel().getSelectedItem().getIdT();
            tab.getItems().remove(tab.getSelectionModel().getSelectedItem());
            PreparedStatement x = (PreparedStatement) conn.prepareStatement("delete from ticket where idT=" + idA);
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
            idA = tab.getSelectionModel().getSelectedItem().getIdT();
            n = priceInput.getText();
            c = clientInput.getText();
            s = flightInput.getText();
            PreparedStatement x = (PreparedStatement) conn.prepareStatement("update ticket set price='" + n + "',idC='" + c + "', idF='" + s + "' where idA='" + idA + "' ");
            x.execute();
            clearInput();
            list.clear();
            ResultSet rs = conn.createStatement().executeQuery("select * from ticket");
            while (rs.next()) {
                list.add(new Ticket(rs.getInt("idT"),rs.getDouble("price"), rs.getInt("idC"), rs.getInt("idF")));
            }
            pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
            clientcol.setCellValueFactory(new PropertyValueFactory<>("idC"));
            flightcol.setCellValueFactory(new PropertyValueFactory<>("idF"));
            tab.setItems(list);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.clear();
        ResultSet rs = null;
        try {
            rs = conn.createStatement().executeQuery("select * from Ticket");
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
                list.add(new Ticket(rs.getInt("idT"),rs.getDouble("price"), rs.getInt("idC"), rs.getInt("idF")));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
        clientcol.setCellValueFactory(new PropertyValueFactory<>("idC"));
        flightcol.setCellValueFactory(new PropertyValueFactory<>("idF"));
        tab.setItems(list);
        nbA.setText(String.valueOf(list.size()));
    }
}