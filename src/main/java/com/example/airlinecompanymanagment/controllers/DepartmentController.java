package com.example.airlinecompanymanagment.controllers;

import com.example.airlinecompanymanagment.models.Department;
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
public class DepartmentController implements Initializable{
        Connection conn = null;

        public DepartmentController() {
            conn = SingletonConnection.getConnection();
        }


        @FXML
        private Text nbD;

        @FXML
        private TextField nameInput;

        @FXML
        private TableColumn<Department, String> namecol;

        @FXML
        private TableView<Department> tab;
        @FXML
        private Button closebtn;
        ObservableList<Department> list = FXCollections.observableArrayList();

        @FXML
        void OnSet(MouseEvent event) {
            String n;

            n = tab.getSelectionModel().getSelectedItem().getName();

            nameInput.setText(String.valueOf(n));


        }
        @FXML
        void onAdd(ActionEvent event) throws SQLException {
            if (this.isInputValid()) {
                String name = nameInput.getText();

                PreparedStatement x = (PreparedStatement) conn.prepareStatement("insert into Department(name) values(?)");

                x.setString(1, name);
                x.execute();

                Department c = new Department(this.nameInput.getText());
                tab.getItems().add(c);
                this.clearInput();
                nbD.setText(String.valueOf(list.size()));
            }



        }
        private void clearInput() {
            this.nameInput.setText("");
        }

        private boolean isInputValid() {
            if (this.nameInput.getText().isEmpty()) {
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
                int idD;
                idD = tab.getSelectionModel().getSelectedItem().getIdD();
                tab.getItems().remove(tab.getSelectionModel().getSelectedItem());
                PreparedStatement x = (PreparedStatement) conn.prepareStatement("delete from department where idDep='" + idD + "'");
                x.execute();
                nbD.setText(String.valueOf(list.size()));
            }}
        @FXML
        void onModify(ActionEvent event) throws SQLException {
            if (this.isInputValid()) {
                String n;
                int idD;
                idD = tab.getSelectionModel().getSelectedItem().getIdD();
                n = nameInput.getText();
                PreparedStatement x = (PreparedStatement) conn.prepareStatement("update department set name='" + n +"'where idDep=" + idD);
                x.execute();
                clearInput();
                list.clear();
                ResultSet rs = conn.createStatement().executeQuery("select * from department");
                while (rs.next()) {
                    list.add(new Department(rs.getInt("idDep"),rs.getString("name")));
                }
                namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
                tab.setItems(list);
            }
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            list.clear();
            ResultSet rs = null;
            try {
                rs = conn.createStatement().executeQuery("select * from department");
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
                    list.add(new Department(rs.getInt("idDep"),rs.getString("name")));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
            tab.setItems(list);
            nbD.setText(String.valueOf(list.size()));
        }

}
