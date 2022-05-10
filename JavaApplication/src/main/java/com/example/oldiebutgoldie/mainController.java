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

    public int globalId;
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

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(ActionEvent event) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM registration WHERE email = '" + usernameTextField.getText() + "'AND aes_decrypt(password, 'Key123') ='"
                + enterPasswordField.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);


            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){

                    String idQuery = "select profileId from registration where email ='" + usernameTextField.getText() + "'";

                    ResultSet getId = statement.executeQuery(idQuery);
                    getId.next();
                    String getProfileId = getId.getString(1);
                    globalId = Integer.parseInt(getProfileId);


                    FXMLLoader fxmlLoader = new FXMLLoader(OldieButGoldieApp.class.getResource("mobileGUI.fxml"));
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
