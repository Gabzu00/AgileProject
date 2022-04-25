module com.example.oldiebutgoldie {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oldiebutgoldie to javafx.fxml;
    exports com.example.oldiebutgoldie;
}