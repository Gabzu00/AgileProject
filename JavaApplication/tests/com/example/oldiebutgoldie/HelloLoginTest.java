package com.example.oldiebutgoldie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.*;

class HelloLoginTest {
    private HelloLogin test;

    @BeforeEach
    public void setUp() {
        test = new HelloLogin();
    }

    @Test
    public void start() {
    }
}