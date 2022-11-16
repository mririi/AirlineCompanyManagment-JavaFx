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
import java.util.ArrayList;
import java.util.List;
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
    private ComboBox<Integer> combo1;
    @FXML
    private ComboBox<Integer> combo2;

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
        combo1.setValue(c);
        combo2.setValue(f);


    }
    @FXML
    void onAdd(ActionEvent event) throws SQLException {
        if (this.isInputValid()) {
            String price = priceInput.getText();
            int flight = combo2.getValue();
            int client = combo1.getValue();

            PreparedStatement x = (PreparedStatement) conn.prepareStatement("insert into Ticket(price,idC,idF) values(?,?,?)");

            x.setString(1, price);
            x.setInt(2, client);
            x.setInt(3, flight);
            x.execute();

            Ticket c = new Ticket(parseDouble(this.priceInput.getText()),flight, client);
            tab.getItems().add(c);
            nbA.setText(String.valueOf(list.size()));
            this.clearInput();
        }



    }
    private void clearInput() {
        this.priceInput.setText("");
        this.combo2.setValue(null);
        this.combo1.setValue(null);
    }

    private boolean isInputValid() {
        if (this.priceInput.getText().isEmpty() || this.combo1.getValue()==null || this.combo2.getValue()==null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input error!");
            alert.setHeaderText(null);
            alert.setContentText("Fields can't be empty!");
            alert.show();
            return false;
        }
        try {
            Double.parseDouble(this.priceInput.getText());
        } catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input error!");
            alert.setHeaderText(null);
            alert.setContentText("Price has to be a double!");
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
            int c;
            int s;
            int idA;
            idA = tab.getSelectionModel().getSelectedItem().getIdT();
            n = priceInput.getText();
            c = combo1.getValue();
            s = combo2.getValue();
            PreparedStatement x = (PreparedStatement) conn.prepareStatement("update ticket set price='" + n + "',idC='" + c + "', idF='" + s + "' where idT='" + idA + "' ");
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
    @FXML
    List<Integer> onidclient () throws SQLException {

        List<Integer> options = new ArrayList<>();

        String query = "Select idC from client";
        PreparedStatement st = (PreparedStatement) conn.prepareStatement(query);
        ResultSet rs = (ResultSet) st.executeQuery();
        while (rs.next()) {
            options.add(rs.getInt("idC"));
        }
        return options;
    }
    @FXML
    List<Integer> onidflight () throws SQLException {

        List<Integer> options = new ArrayList<>();

        String query = "Select idF from flight";
        PreparedStatement st = (PreparedStatement) conn.prepareStatement(query);
        ResultSet rs = (ResultSet) st.executeQuery();
        while (rs.next()) {
            options.add(rs.getInt("idF"));
        }
        return options;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            combo1.setItems(FXCollections.observableArrayList(onidclient()));
            combo2.setItems(FXCollections.observableArrayList(onidflight()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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