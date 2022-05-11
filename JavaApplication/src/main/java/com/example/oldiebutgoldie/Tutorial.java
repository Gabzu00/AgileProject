package com.example.oldiebutgoldie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Tutorial extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(OldieButGoldieApp.class.getResource("tutorial.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("OldieButGoldie");
        stage.setScene(scene);

        stage.show();
    }
}
