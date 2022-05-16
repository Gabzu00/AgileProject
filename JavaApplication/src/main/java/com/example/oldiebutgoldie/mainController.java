package com.example.oldiebutgoldie;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class mainController extends OldieButGoldieApp {


    public static Person loginUser = null;
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;


    public void loginButtonOnAction(ActionEvent event){

        if (!usernameTextField.getText().isBlank() && !enterPasswordField.getText().isBlank()){
            validateLogin(event);
        }else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    public void cancelButtonOnAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public Person validateLogin(ActionEvent event) {
        Person person = new Person();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM user WHERE email = '" + usernameTextField.getText() + "'AND aes_decrypt(password, 'Key123') ='"
                + enterPasswordField.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){

                    String idQuery = "SELECT * FROM user WHERE email = '" + usernameTextField.getText() + "'";

                    ResultSet getData = statement.executeQuery(idQuery);
                    getData.next();
                    person.setFirstName(getData.getString("firstName"));
                    person.setLastName(getData.getString("lastName"));
                    person.setEmail(getData.getString("email"));
                    person.setPassword(getData.getString("email"));
                    person.setId(getData.getInt("userId"));
                    person.setDescription(getData.getString("description"));
                    person.setCountry(getData.getString("country"));
                    person.setCity(getData.getString("city"));
                    person.setPicture(getData.getString("image"));
                    person.setAge(getData.getInt("age"));

                    loginUser = person;

                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tutorial.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(fxmlLoader.load());

                    stage.setTitle("Liked!");
                    stage.setScene(scene);
                    stage.show();
                }else{
                    loginMessageLabel.setText("Invalid login. Please try again.");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return person;
    }

    public void createAccountForm(){
        try{
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignUp.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
