package com.example.oldiebutgoldie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignUpController {

    @FXML
    private TextField confirmPassword;
    @FXML
    private TextField email;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField password;
    @FXML
    private Button cancelButton;
    @FXML
    private Label registrationMessageLebel;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private Label youAreSetLabel;

    @FXML
    void registerButton(ActionEvent event) {
        if (password.getText().equals(confirmPassword.getText())){
            registerUser();

        }else{
            confirmPasswordLabel.setText("Password does not match!");
        }

    }

    public void cancelButton() throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(true);

    }

    public void registerUser(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connection = connectNow.getConnection();
        try {
            Statement statement = connection.createStatement();

            String firstname = firstName.getText();
            String lastname = lastName.getText();
            String Email = email.getText();
            String Password = password.getText();

            String profileId = "select MAX(profileId) from registration";
            ResultSet getProfileId = statement.executeQuery(profileId);
            getProfileId.next();

            String setProfileId = getProfileId.getString(1);
            int profileIdPlusOne = Integer.parseInt(setProfileId) + 1;

            String insertFields = "insert into registration (firstName, lastName, email, password, profileId) VALUES ('";
            String insertValues = firstname + "','" + lastname + "','" + Email + "',aes_encrypt('" + Password + "','Key123'),'" + profileIdPlusOne + "')";
            String insertToRegister = insertFields + insertValues;

            statement.executeUpdate(insertToRegister);

            String insertFields2 = "insert into profile (profileId) VALUES ('";
            String insertValues2 = profileIdPlusOne + "')";
            String insertToRegister2 = insertFields2 + insertValues2;

            statement.executeUpdate(insertToRegister2);

            registrationMessageLebel.setText("User registered successfully!");

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }


}

}
