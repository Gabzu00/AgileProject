package com.example.oldiebutgoldie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection getConnection() {
        String dsn = "jdbc:mysql://localhost/"
                + "oldiebutgoldie"
                + "?user=Sam"
                + "&password=P@ssw0rd";

        try {
            Connection connection = DriverManager.getConnection(dsn);
            return connection;


        } catch (SQLException exception) {
            System.out.println("Error!");
            System.out.println("SQLException: " + exception.getMessage());
            System.out.println("SQLState: " + exception.getSQLState());
            System.out.println("VendorError: " + exception.getErrorCode());
        }
        return null;
    }}
