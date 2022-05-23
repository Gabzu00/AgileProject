package com.example.oldiebutgoldie;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MatchesController implements Initializable {

    @FXML
    public ChoiceBox<String> choiceBox;

    private final Person login = MainController.loginUser;


    @FXML
    public void choiceBoxMethod(ArrayList<String> firstNameList, ArrayList<String> lastNameList) {

        int firstNameListSize = firstNameList.size();

        for (int i = 0; i < firstNameListSize; i++) {
            String nameToAdd = firstNameList.get(i) + " " + lastNameList.get(i);
            choiceBox.getItems().add(nameToAdd);
        }
    }

    public ArrayList<Integer> createMatchesList() {
        ArrayList<Integer> usersWhoLiked = new ArrayList<>();
        ArrayList<Integer> usersWhoAreLiked = new ArrayList<>();
        ArrayList<Integer> matchesList = new ArrayList<>();

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String SQL1 = "SELECT userId FROM likedUsers WHERE likedUserId = " +
                login.getId() + ";";
        String SQL2 = "SELECT likedUserId FROM likedUsers WHERE userId = " +
                login.getId() + ";";

        try {
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
                    matchesList.add(i);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return matchesList;
    }
    @FXML
    public void exitButton() {
        System.exit(0);
    }

    public ArrayList<String> getFirstNameList(ArrayList<Integer> matchesList) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        ArrayList<String> firstNameList = new ArrayList<>();

        for (int i : matchesList) {

            try {
                Statement statement = connection.createStatement();
                String SQL = "SELECT firstName FROM user WHERE userId = " + i + ";";
                ResultSet resultSet = statement.executeQuery(SQL);
                resultSet.next();

                firstNameList.add(resultSet.getString("firstName"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return firstNameList;
    }

    public ArrayList<String> getLastNameList(ArrayList<Integer> matchesList) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        ArrayList<String> lastNameList = new ArrayList<>();

        for (int i : matchesList) {

            try {
                Statement statement = connection.createStatement();
                String SQL = "SELECT lastName FROM user WHERE userId = " + i + ";";
                ResultSet resultSet = statement.executeQuery(SQL);
                resultSet.next();

                lastNameList.add(resultSet.getString("lastName"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return lastNameList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxMethod(getFirstNameList(createMatchesList()), getLastNameList(createMatchesList()));
    }
}
