package com.example.oldiebutgoldie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class OldieController {

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    @FXML
    public ImageView image;
    @FXML
    public Label labelName;
    @FXML
    public Label labelAge;
    @FXML
    public Label labelDescription;
    @FXML
    private Button EditProfileButton;


    @FXML
    private void initialize() {
        personInfo();
    }

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
    public void yesButton(MouseEvent event){
        personInfo();
    }

    @FXML
    public void noButton(MouseEvent event) throws IOException{
        fxmlLoader = new FXMLLoader(OldieButGoldieApp.class.getResource("DidNotLikeScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());

        stage.setTitle("Did not like!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public Person personInfo() {

        Person person = personInfoLoad();


        labelName.setText(person.getFirstName());
        labelAge.setText(person.getAge());
        labelDescription.setText(person.getDescription());

        URL path = OldieButGoldieApp.class.getResource(person.getPicture());
        ImageView setPicture = new ImageView (String.valueOf(path));
        image.setImage(setPicture.getImage());

        return person;
    }

    @FXML
    public Person personInfoLoad() {
        Random rand = new Random();
        int randId = rand.nextInt(1, 5);
        Person person = null;
        DatabaseConnection db = new DatabaseConnection();

        try {
            Connection connection = db.getConnection();
            Statement stmt = connection.createStatement();

            String SQL1 = "SELECT image, age, description, profileId FROM profile WHERE profileId = " + randId + ";";

            ResultSet rs1 = stmt.executeQuery(SQL1);
            rs1.next();

            int profileId = rs1.getInt("profileId");
            String picture = rs1.getString("image");
            String age = rs1.getString("age");
            String description = rs1.getString("description");

            String SQL2 = "SELECT firstName FROM registration WHERE profileId = " + randId + ";";

            ResultSet rs2 = stmt.executeQuery(SQL2);
            rs2.next();

            String firstName = rs2.getString("firstName");

            person = new Person(profileId, firstName, age, description, picture);

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }


        return person;
    }

    public void editProfile(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) EditProfileButton.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EditProfile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Edit profile");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}