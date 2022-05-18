package com.example.oldiebutgoldie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class EditProfileController {

    @FXML
    private TextField ageLabel;
    @FXML
    private TextField cityLabel;
    @FXML
    private TextField countryLabel;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextField imageLabel;
    @FXML
    private Button EditProfileButton;

    @FXML
    void doneButton() throws IOException {
        updateProfile();

        Stage stage1 = (Stage) EditProfileButton.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mobileGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage primaryStage = new Stage();
        primaryStage.setTitle("OldieButGoldie");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public void updateProfile(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connection = connectNow.getConnection();

        try {
            Statement statement = connection.createStatement();
            Person getData = mainController.loginUser;

            String age = ageLabel.getText();
            String city = cityLabel.getText();
            String country = countryLabel.getText();
            String image = imageLabel.getText();
            String description = descriptionTextArea.getText();

            String query = "UPDATE user SET age = '" + age + "', city = '" + city + "', country = '" + country + "'," +
                    "image = '" + image + "', description = '" + description + "' WHERE userId = '" + getData.getId() + "'" ;

            statement.executeUpdate(query);



        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
