package com.example.contactpage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CheckFormController implements Initializable {

    @FXML
    private ImageView infoImageView;
    @FXML
    private Label checkName;
    @FXML
    private Label checkLName;
    @FXML
    private Label checkPhone;
    @FXML
    private Label checkMail;
    @FXML
    private Label checkAddress;
    @FXML
    private Label checkSubject;
    @FXML
    private Button sendButton;
    @FXML
    private Button returnButton;
    private ContactInfo contactInfo;

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
        showInfo();
    }

    public void showInfo() {
        checkName.setText(contactInfo.getFirstName());
        checkLName.setText(contactInfo.getLastName());
        checkPhone.setText(contactInfo.getPhoneNumber());
        checkMail.setText(contactInfo.geteMail());
        checkAddress.setText(contactInfo.getAddress());
        checkSubject.setText(contactInfo.getSubject());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File infoFile = new File("C:\\Users\\abdul\\OneDrive\\Masaüstü\\Java\\ContactPage\\src\\main\\resources\\images\\info.png");
        Image infoImage = new Image(infoFile.toURI().toString());
        infoImageView.setImage(infoImage);
    }

    public void congratAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Congratulations");
        alert.setHeaderText("Process successful");
        alert.setContentText("Your mail has been sent successfully");
        alert.showAndWait();
    }

    public void setSendButtonOnAction(ActionEvent event) {
        congratAlert();
        Stage stage = (Stage) sendButton.getScene().getWindow();
        stage.close();
    }

    public void setReturnButtonOnAciton(ActionEvent event) {
            Stage restartStage = (Stage) returnButton.getScene().getWindow();
            Main main = new Main();
            main.start(restartStage);
    }

}
