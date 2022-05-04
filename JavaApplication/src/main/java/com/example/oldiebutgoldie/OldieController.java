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
    public void changePerson() {
        URL path = OldieButGoldieApp.class.getResource("Images/Albert.jpg");
        Image image2 = new Image (String.valueOf(path));
        image.setImage(image2);
    }

    @FXML
    public Person personInfo() throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();
        Statement stmt = connection.createStatement();
        String SQL = "SELECT * FROM user WHERE userId = 1;";
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