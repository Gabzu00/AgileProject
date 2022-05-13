package com.example.oldiebutgoldie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OldieButGoldieAppTest {
    private OldieButGoldieApp test;
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @BeforeEach
    void setUp() {
        test = new OldieButGoldieApp();
    }

    @Test
    void startTest() {
        assertThrows(javafx.fxml.LoadException.class, () -> test.start(stage));
    }
}