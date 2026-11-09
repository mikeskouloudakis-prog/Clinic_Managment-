package com.example.clinicmanagment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource("dashboard.fxml"));

        Scene scene =
                new Scene(fxmlLoader.load());

        scene.getStylesheets().add(
                getClass().getResource("style.css")
                        .toExternalForm());

        stage.setTitle("Clinic Management System");

        stage.setScene(scene);

        stage.setWidth(1600);

        stage.setHeight(900);

        stage.centerOnScreen();

        stage.show();
    }
}
