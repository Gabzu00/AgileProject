module com.example.oldiebutgoldie {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.java;

    opens com.example.oldiebutgoldie to javafx.fxml;
    exports com.example.oldiebutgoldie;
}