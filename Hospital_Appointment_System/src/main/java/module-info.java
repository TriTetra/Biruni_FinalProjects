module com.example.meetingcalender {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.meetingcalender to javafx.fxml;
    exports com.example.meetingcalender;
}