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

    private ArrayList<Integer> passedUsers = new ArrayList<>();

    private Person login = mainController.loginUser;

    @FXML
    private void initialize(){
        this.passedUsers.add(login.getId());
        int randId = generateRandomId();

        this.passedUsers.add(randId);

        displayPersonInfo(personInfo(randId));
    }

    public int getMaxUserId() {
        Connection connection = connectToDatabase();
        int idSize = 0;

        try {
            Statement statement = connection.createStatement();

            String getIDs = "select MAX(userId) from user";

            ResultSet queryResult = statement.executeQuery(getIDs);
            queryResult.next();
            idSize = queryResult.getInt(1);

        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return idSize;
    }

    public boolean checkIfMoreUsers(int maxUserId, int loginId) {
        boolean moreUsers = false;

        for (int i = 1; i <= maxUserId; i++) {
            if (!this.passedUsers.contains(i)) {
                moreUsers = true;
                break;
            }
        }

        return moreUsers;
    }

    public int generateRandomId() {
        int maxUserId = getMaxUserId();

        Random rand = new Random();
        int randId = rand.nextInt(1, maxUserId + 1);

        boolean moreUsers = checkIfMoreUsers(maxUserId, randId);

        if (moreUsers) {
            while (randId == login.getId() || this.passedUsers.contains(randId)) {
                randId = rand.nextInt(1, maxUserId + 1);
            }
        } else {
            noMoreUsers();
        }

        return randId;
    }

    @FXML
    public void yesButton(){
        int randId = generateRandomId();
        this.passedUsers.add(randId);

        displayPersonInfo(personInfo(randId));
    }

    @FXML
    public void noButton() throws IOException{
        int randId = generateRandomId();
        this.passedUsers.add(randId);

        displayPersonInfo(personInfo(randId));
    }

    @FXML
    public void noMoreUsers() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NoMoreUsersScene.fxml"));
        stage = (Stage)image.getScene().getWindow();
        Scene scene = null;

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setTitle("No more users to view");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
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
        URL path = OldieButGoldieApp.class.getResource(person.getPicture());
        ImageView setPicture = new ImageView (String.valueOf(path));
        image.setImage(setPicture.getImage());
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
}