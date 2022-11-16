package com.example.airlinecompanymanagment.controllers;

import com.example.airlinecompanymanagment.models.Airplane;
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

import static java.lang.Integer.parseInt;

public class AirplaneController implements Initializable {
    Connection conn = null;

    public AirplaneController() {
        conn = SingletonConnection.getConnection();
    }
    @FXML
    private Button addbtn;

    @FXML
    private Button deletebtn;

    @FXML
    private Button modifybtn;

    @FXML
    private TextField nameInput;

    @FXML
    private TableColumn<Airplane, String> namecol;

    @FXML
    private Text nbA;

    @FXML
    private TextField statusInput;

    @FXML
    private TableColumn<Airplane, String> statuscol;

    @FXML
    private TableView<Airplane> tab;
    ObservableList<Airplane> list = FXCollections.observableArrayList();

    @FXML
    void OnSet(MouseEvent event) {
        String n;
        int a;

        n = tab.getSelectionModel().getSelectedItem().getName();
        a = tab.getSelectionModel().getSelectedItem().getStatus();

        nameInput.setText(String.valueOf(n));
        statusInput.setText(String.valueOf(a));


    }
    @FXML
    void onAdd(ActionEvent event) throws SQLException {
        if (this.isInputValid()) {
            String name = nameInput.getText();
            int status = parseInt(statusInput.getText());

            PreparedStatement x = (PreparedStatement) conn.prepareStatement("insert into Airplane(name,status) values(?,?)");

            x.setString(1, name);
            x.setInt(2, status);
            x.execute();

            Airplane c = new Airplane(name, status);
            tab.getItems().add(c);
            nbA.setText(String.valueOf(list.size()));
            this.clearInput();
        }



    }
    private void clearInput() {
        this.nameInput.setText("");
        this.statusInput.setText("");
    }

    private boolean isInputValid() {
        if (this.nameInput.getText().isEmpty()  || this.statusInput.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input error!");
            alert.setHeaderText(null);
            alert.setContentText("Fields can't be empty!");
            alert.show();
            return false;
        }
        try {
            Integer.parseInt(this.statusInput.getText());
        } catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input error!");
            alert.setHeaderText(null);
            alert.setContentText("Status has to be a number!");
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
            idA = tab.getSelectionModel().getSelectedItem().getIdAirplane();
            tab.getItems().remove(tab.getSelectionModel().getSelectedItem());
            PreparedStatement x = (PreparedStatement) conn.prepareStatement("delete from airplane where idAirplane=" + idA);
            x.execute();
            nbA.setText(String.valueOf(list.size()));
        }}
    @FXML
    void onModify(ActionEvent event) throws SQLException {
        if (this.isInputValid()) {
            String n;
            String s;
            int idA;
            idA = tab.getSelectionModel().getSelectedItem().getIdAirplane();
            n = nameInput.getText();
            s = statusInput.getText();
            PreparedStatement x = (PreparedStatement) conn.prepareStatement("update airplane set name='" + n +"', status='" + s + "' where idAirplane='" + idA + "' ");
            x.execute();
            clearInput();
            list.clear();
            ResultSet rs = conn.createStatement().executeQuery("select * from airplane");
            while (rs.next()) {
                list.add(new Airplane(rs.getInt("idAirplane"),rs.getString("name"), rs.getInt("status")));
            }
            namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
            statuscol.setCellValueFactory(new PropertyValueFactory<>("status"));
            tab.setItems(list);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.clear();
        ResultSet rs = null;
        try {
            rs = conn.createStatement().executeQuery("select * from Airplane");
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
                list.add(new Airplane(rs.getInt("idAirplane"),rs.getString("name"), rs.getInt("status")));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        statuscol.setCellValueFactory(new PropertyValueFactory<>("status"));
        tab.setItems(list);
        nbA.setText(String.valueOf(list.size()));
    }
}
