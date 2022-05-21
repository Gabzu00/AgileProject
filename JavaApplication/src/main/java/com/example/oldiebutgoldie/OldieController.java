package com.example.oldiebutgoldie;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class OldieController {
    @FXML
    public ImageView image;
    @FXML
    public ChoiceBox<String> choiceBox;
    @FXML
    public ImageView notificationBell;
    @FXML
    public Label labelName;
    @FXML
    public Label labelAge;
    @FXML
    public Label labelDescription;
    @FXML
    public Button EditProfileButton;

    public int currentPersonId;

    private final ArrayList<Integer> passedUsers = new ArrayList<>();

    public final ArrayList<Integer> matches = new ArrayList<>();

    private final Person login = mainController.loginUser;

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

    public boolean checkIfMoreUsers(int maxUserId) {
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

        boolean moreUsers = checkIfMoreUsers(maxUserId);

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
        Connection connection = connectToDatabase();

        try {
            insertIntoLikedUsers(connection, currentPersonId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.passedUsers.add(currentPersonId);
        int randId = generateRandomId();


        match(connection);

        javafx.scene.image.Image image1 = new javafx.scene.image.Image(getClass().getResource("Images/Bell_notification.png").toExternalForm());
        javafx.scene.image.Image image2 = new javafx.scene.image.Image(getClass().getResource("Images/Bell.png").toExternalForm());

        ImageView imageNotification = new ImageView(image1);
        ImageView imageRegular = new ImageView(image2);

        if (checkMatches()) {
            notificationBell.setImage(imageNotification.getImage());
        } else {
            notificationBell.setImage(imageRegular.getImage());
        }

        displayPersonInfo(personInfo(randId));
    }

    @FXML
    public void noButton() {
        int randId = generateRandomId();
        this.passedUsers.add(currentPersonId);

        match(connectToDatabase());

        javafx.scene.image.Image image1 = new javafx.scene.image.Image(getClass().getResource("Images/Bell_notification.png").toExternalForm());
        javafx.scene.image.Image image2 = new javafx.scene.image.Image(getClass().getResource("Images/Bell.png").toExternalForm());

        ImageView imageNotification = new ImageView(image1);
        ImageView imageRegular = new ImageView(image2);

        if (checkMatches()) {
            notificationBell.setImage(imageNotification.getImage());
        } else {
            notificationBell.setImage(imageRegular.getImage());
        }

        displayPersonInfo(personInfo(randId));
    }

    public void match(Connection connection) {
        ArrayList<Integer> usersWhoLiked = new ArrayList<>();
        ArrayList<Integer> usersWhoAreLiked = new ArrayList<>();

        try {
            String SQL1 = "SELECT userId FROM likedUsers WHERE likedUserId = " +
                    login.getId() + ";";
            String SQL2 = "SELECT likedUserId FROM likedUsers WHERE userId = " +
                    login.getId() + ";";

            Statement statement = connection.createStatement();
            ResultSet rs1 = statement.executeQuery(SQL1);

            while (rs1.next()) {
                usersWhoLiked.add(rs1.getInt(1));
            }

            ResultSet rs2 = statement.executeQuery(SQL2);

            while (rs2.next()) {
                usersWhoAreLiked.add(rs2.getInt(1));
            }

            for (int i : usersWhoLiked) {
                if (usersWhoAreLiked.contains(i)) {
                    this.matches.add(i);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkMatches() {
        boolean matchesBool = false;

        if (this.matches.size() > 0) {
            matchesBool = true;
        }

        return matchesBool;
    }

    @FXML
    public void noMoreUsers() {
        Stage stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NoMoreUsersScene.fxml"));
        stage = (Stage)image.getScene().getWindow();
        Scene scene;

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setTitle("OldieButGoldie");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public void insertIntoLikedUsers(Connection connection, int likedUserid) throws SQLException {
        Statement stmt = connection.createStatement();
        String SQL = "INSERT INTO likedUsers (userId, likedUserId)" +
                "VALUES (" + login.getId() + "," + likedUserid +
                ");";
        stmt.executeUpdate(SQL);
    }

    public Person personInfo(int randId) {
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

        return db.getConnection();
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
        this.currentPersonId = person.getId();
        labelName.setText(person.getFirstName());
        if (person.getAge() == 0){
            labelAge.setText("");
        }else{
            labelAge.setText(Integer.toString(person.getAge()));
        }
        labelDescription.setText(person.getDescription());


        if (person.getPicture() != null){
            URL path = OldieButGoldieApp.class.getResource(person.getPicture());
            ImageView setPicture = new ImageView (String.valueOf(path));
            image.setImage(setPicture.getImage());
        }else{
            image.setImage(null);
        }

    }

    public void editProfile() throws IOException {
        Stage stage1 = (Stage) EditProfileButton.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EditProfile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage primaryStage = new Stage();
        primaryStage.setTitle("OldieButGoldie");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void bellButton(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MatchesScene.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("OldieButGoldie");
        stage.setScene(scene);
        stage.show();

    }

    public int getUserId(ResultSet rs) throws SQLException {

        return rs.getInt("userId");
    }

    public String getFirstName(ResultSet rs) throws SQLException {

        return rs.getString("firstName");
    }

    public int getAge(ResultSet rs) throws SQLException {

        return rs.getInt("age");
    }

    public String getDescription(ResultSet rs) throws SQLException {

        return rs.getString("description");
    }

    public String getPicture(ResultSet rs) throws SQLException {

        return rs.getString("image");
    }

}