package com.example.oldiebutgoldie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class OldieController {

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    public void switchToLikeScene(MouseEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(OldieButGoldieApp.class.getResource("LikeScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());

        stage.setTitle("Liked!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToDidNotLikeScene(MouseEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(OldieButGoldieApp.class.getResource("DidNotLikeScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());

        stage.setTitle("Did not like!");
        stage.setScene(scene);
        stage.show();
    }
}