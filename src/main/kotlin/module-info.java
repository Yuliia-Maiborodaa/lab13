module org.example.lab13 {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens org.example.lab13 to javafx.fxml;
    exports org.example.lab13;
}