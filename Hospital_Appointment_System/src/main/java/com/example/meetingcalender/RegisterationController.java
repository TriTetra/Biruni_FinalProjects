package com.example.meetingcalender;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegisterationController implements Initializable {

    ClientForm clientForm;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField idNumber;
    @FXML
    private Button continueButton;
    @FXML
    private Button endButton;
    @FXML
    private Label inputProcess;
    @FXML
    private ImageView medicalTeamImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File medicalTeam = new File("C:\\Users\\abdul\\OneDrive\\Masaüstü\\Java\\MeetingCalender\\src\\main\\resources\\images\\medical-team.png");
        Image medicalTeamImage = new Image(medicalTeam.toURI().toString());
        medicalTeamImageView.setImage(medicalTeamImage);
    }

    public void setClientForm() {
        String firstNameOf = firstName.getText();
        String lastNameOf = lastName.getText();
        String idNumberOf = idNumber.getText();

        clientForm = new ClientForm(firstNameOf, lastNameOf, idNumberOf,null,null);
    }

    public void setContinueButtonOnAction(ActionEvent event) {
        if (isInputValid()){
            setClientForm();
            goCalendarTimeController();
            Stage stage = (Stage) continueButton.getScene().getWindow();
            stage.close();
        }else {
            inputProcess.setText("Lütfen gerekli yerleri doldurun..!");
        }
    }

    public boolean isInputValid() {
        String firstNameOf = firstName.getText();
        String lastNameOf = lastName.getText();
        String idNumberOf = idNumber.getText();

        return !firstNameOf.isEmpty() && !lastNameOf.isEmpty() && !idNumberOf.isEmpty();
    }

    public void goCalendarTimeController(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("timeCalendar.fxml"));
            Parent root = fxmlLoader.load();
            CalendarTimeController calendarTimeController = fxmlLoader.getController();
            calendarTimeController.setClient(clientForm);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
            Stage stage = new Stage();
            stage.getIcons().add(icon());
            stage.setTitle("Meeting Calendar");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image icon(){
        File iconFile = new File("C:\\Users\\abdul\\OneDrive\\Masaüstü\\Java\\MeetingCalender\\src\\main\\resources\\images\\health.png");
        return new Image(iconFile.toURI().toString());
    }
}
