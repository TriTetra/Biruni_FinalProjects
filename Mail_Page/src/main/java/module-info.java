module com.example.contactpage {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.contactpage to javafx.fxml;
    exports com.example.contactpage;
}