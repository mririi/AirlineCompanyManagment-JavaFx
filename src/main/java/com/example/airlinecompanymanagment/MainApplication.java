package com.example.airlinecompanymanagment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage.setTitle("Home");
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
//        Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
//        stage.setTitle("SignUp");
//        Scene scene = new Scene(root);
//        stage.setResizable(false);
//        //stage.initStyle(StageStyle.UNDECORATED);
//        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
//        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
//        stage.getIcons().add(icon);
//        //stage.setMaximized(true);
//        //stage.setFullScreen(true);
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}