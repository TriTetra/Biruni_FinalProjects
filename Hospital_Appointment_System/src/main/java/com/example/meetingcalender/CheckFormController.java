package com.example.meetingcalender;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckFormController implements Initializable{

    @FXML
    private Label checkName1;
    @FXML
    private Label checkName;
    @FXML
    private Label checkID;
    @FXML
    private Label checkDay;
    @FXML
    private Label checkTime;
    @FXML
    private Button closeButton;
    ClientForm clientForm;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setInformation(ClientForm clientForm){
        this.clientForm = clientForm;
        checkName1.setText(clientForm.getLastName());
        checkName.setText(clientForm.getFirstName());
        checkID.setText(clientForm.getIdNumber());
        checkDay.setText(clientForm.getMeetingDay());
        checkTime.setText(clientForm.getMeetingTime());
    }

    public void setCloseButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
