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
        Random rand = new Random();
        int randId = rand.nextInt(1, 5);

        displayPersonInfo(personInfo(randId));
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
        Random rand = new Random();
        int randId = rand.nextInt(1, 5);
        personInfo(randId);
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
    public Person personInfo(int id) {
        int randId = id;
        Person person = null;

        try {
            person = createPerson(createSQL(connectToDatabase(), randId));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }

    public Connection connectToDatabase() {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();

        return connection;
    }

    public ResultSet createSQL(Connection connection, int randId) throws SQLException {
        Statement stmt = connection.createStatement();
        String SQL = "SELECT * FROM user WHERE userId = " + randId + ";";
        ResultSet rs = stmt.executeQuery(SQL);
        rs.next();

        return rs;
    }

    public int getUserId(ResultSet rs) throws SQLException {
        int userId = rs.getInt("userId");

        return userId;
    }

    public String getFirstName(ResultSet rs) throws SQLException {
        String firstName = rs.getString("firstName");

        return firstName;
    }

    public int getAge(ResultSet rs) throws SQLException {
        int age = rs.getInt("age");

        return age;
    }

    public String getDescription(ResultSet rs) throws SQLException {
        String description = rs.getString("description");

        return description;
    }

    public String getPicture(ResultSet rs) throws SQLException {
        String picture = rs.getString("image");

        return picture;
    }

    public Person createPerson(ResultSet rs) throws SQLException {
        Person person = new Person();

        person.setId(getUserId(rs));
        person.setFirstName(getFirstName(rs));
        person.setAge(getAge(rs));
        person.setDescription(getDescription(rs));
        person.setPicture(getPicture(rs));

        return person;
    }

    public void displayPersonInfo(Person person) {
        labelName.setText(person.getFirstName());
        labelAge.setText(Integer.toString(person.getAge()));
        labelDescription.setText(person.getDescription());
    }

    /*@FXML
    public Person personInfo() {

        Person person = personInfoLoad();


        labelName.setText(person.getFirstName());
        labelAge.setText(person.getAge());
        labelDescription.setText(person.getDescription());

        URL path = OldieButGoldieApp.class.getResource(person.getPicture());
        ImageView setPicture = new ImageView (String.valueOf(path));
        image.setImage(setPicture.getImage());

        return person;
    }*/

    @FXML
    public Person personInfoLoad() {
        Random rand = new Random();
        int randId = rand.nextInt(1, 5);

        //DatabaseConnection db = new DatabaseConnection();

        try {
            Person person = personInfo(randId);
            //Connection connection = db.getConnection();
            //Statement stmt = connection.createStatement();

            //String SQL1 = "SELECT image, age, description, profileId FROM profile WHERE profileId = " + randId + ";";

            //ResultSet rs1 = stmt.executeQuery(SQL1);
            //rs1.next();

            /*int profileId = rs1.getInt("profileId");
            String picture = rs1.getString("image");
            String age = rs1.getString("age");
            String description = rs1.getString("description");

            String SQL2 = "SELECT firstName FROM registration WHERE profileId = " + randId + ";";

            ResultSet rs2 = stmt.executeQuery(SQL2);
            rs2.next();

            String firstName = rs2.getString("firstName");*/

            //person = new Person(profileId, firstName, age, description, picture);

            return person;

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return null;
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