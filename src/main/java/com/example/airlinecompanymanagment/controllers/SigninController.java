package com.example.airlinecompanymanagment.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SigninController {

    @FXML
    private TextField emailInput;

    @FXML
    private TextField passwordInput;

    @FXML
    private Button signinbtn;
    private boolean isInputValid() {
        if (this.emailInput.getText().isEmpty()  || this.passwordInput.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input error!");
            alert.setHeaderText(null);
            alert.setContentText("Les champs ne doivent pas etre vide!");
            alert.show();
            return false;
        }
        return true;
    }
//    @FXML
//    List<String> oniduser () throws SQLException {
//
//        List<Integer> options = new ArrayList<>();
//
//        String query = "Select email,password from client";
//        PreparedStatement st = (PreparedStatement) conn.prepareStatement(query);
//        ResultSet rs = (ResultSet) st.executeQuery();
//        while (rs.next()) {
//            options.add(rs.getString("email"),rs.getString("password"));
//        }
//        return options;
//    }
    @FXML
    void onPress(ActionEvent event) {
        if (this.isInputValid()) {
            String email = emailInput.getText();
            String password = passwordInput.getText();
        }
        }

}
