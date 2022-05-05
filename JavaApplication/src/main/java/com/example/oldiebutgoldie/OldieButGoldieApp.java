package com.example.oldiebutgoldie;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class OldieButGoldieApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OldieButGoldieApp.class.getResource("mobileGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("OldieButGoldie");
        stage.setScene(scene);
        stage.show();
    }

    public int testThis(int a, int b) {
        int c = a + b;
        return c;
    }

    public static void main(String[] args) {
        launch();
    }
}