module com.example.hospitalsalarymanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hospitalsalarymanagement to javafx.fxml;
    exports com.example.hospitalsalarymanagement;
}