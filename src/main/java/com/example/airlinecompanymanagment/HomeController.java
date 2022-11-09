package com.example.airlinecompanymanagment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private AnchorPane ap;

    @FXML
    private BorderPane bp;

    @FXML
    void managmentAction(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Dash.fxml"));
        stage.setTitle("Dashboard");
        Scene scene = new Scene(root,1020,450);
        stage.setResizable(false);
        //stage.initStyle(StageStyle.UNDECORATED);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        stage.getIcons().add(icon);
        //stage.setMaximized(true);
        //stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void overviewAction(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Overview.fxml"));
        stage.setTitle("Overview");
        Scene scene = new Scene(root,850,450);
        stage.setResizable(false);
        //stage.initStyle(StageStyle.UNDECORATED);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        stage.getIcons().add(icon);
        //stage.setMaximized(true);
        //stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

}
