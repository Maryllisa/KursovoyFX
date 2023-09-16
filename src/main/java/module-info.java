module com.example.kursovoy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.kursovoy to javafx.fxml;
    exports com.example.kursovoy;
    exports men_student;
    opens men_student to javafx.fxml;
}