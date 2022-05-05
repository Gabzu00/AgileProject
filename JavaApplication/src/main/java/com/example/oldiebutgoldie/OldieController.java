package com.example.oldiebutgoldie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private ImageView image;
    @FXML
    private Label labelName;
    @FXML
    private Label labelAge;
    @FXML
    private Label labelDescription;


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

    @FXML
    public void changePerson() throws SQLException {
        Person person = personInfo();
        int imageId = person.getId();
        URL path2 = OldieButGoldieApp.class.getResource("Images/Albert.jpg");
        URL path3 = OldieButGoldieApp.class.getResource("Images/greg.jpg");
        URL path4 = OldieButGoldieApp.class.getResource("Images/leif.jpg");
        Image image2 = new Image (String.valueOf(path2));
        Image image3 = new Image (String.valueOf(path3));
        Image image4 = new Image (String.valueOf(path4));
        if (imageId == 2) {
            image.setImage(image2);
        }
        else if (imageId == 3) {
            image.setImage(image3);
        }
        else if (imageId == 4) {
            image.setImage(image4);
        }
    }

    @FXML
    public Person personInfo() throws SQLException {
        Random rand = new Random();
        int randId = rand.nextInt(2,5);
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();
        Statement stmt = connection.createStatement();
        String SQL = "SELECT * FROM user WHERE userId = " + randId + ";";
        ResultSet rs = stmt.executeQuery(SQL);
        rs.next();
        int userId = rs.getInt("userId");
        String firstName = rs.getString("firstName");
        String age = rs.getString("age");
        String description = rs.getString("description");
        Person person = new Person(userId, firstName, age, description);
        labelName.setText(person.getFirstName());
        labelAge.setText(person.getAge());
        labelDescription.setText(person.getDescription());
        return person;
    }
}