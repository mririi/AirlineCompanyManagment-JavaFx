package com.example.airlinecompanymanagment;

import com.example.airlinecompanymanagment.controllers.SingletonConnection;
import com.example.airlinecompanymanagment.models.Airplane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class SignupController {
    Connection conn = null;
    public SignupController(){
        conn = SingletonConnection.getConnection();
    }
    @FXML
    private Button addbtn;

    @FXML
    private TextField emailInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private Button signupbtn;
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

    @FXML
    void onPress(ActionEvent event) throws SQLException {
        if (this.isInputValid()) {
            String email = emailInput.getText();
            String password = passwordInput.getText();

            PreparedStatement x = (PreparedStatement) conn.prepareStatement("insert into User(email,password) values(?,?)");

            x.setString(1, email);
            x.setString(2, password);
            x.execute();
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
                stage.setTitle("SignIn");
                Scene scene = new Scene(root);
                stage.setResizable(false);
                //stage.initStyle(StageStyle.UNDECORATED);
                scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                Image icon = new Image(getClass().getResourceAsStream("icon.png"));
                stage.getIcons().add(icon);
                //stage.setMaximized(true);
                //stage.setFullScreen(true);
                stage.setScene(scene);
                stage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

}
