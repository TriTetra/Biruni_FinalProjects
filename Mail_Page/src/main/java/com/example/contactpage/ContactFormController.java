package com.example.contactpage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ContactFormController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private Button sendButton;
    @FXML
    private ImageView pettieImageView;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField mailTextField;
    @FXML
    private TextArea addressTextArea;
    @FXML
    private TextArea subjectTextArea;

    ContactInfo contactInfo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File bannerFile = new File("C:\\Users\\abdul\\OneDrive\\Masaüstü\\Java\\ContactPage\\src\\main\\resources\\images\\Pettier.png");
        Image pettieImage = new Image(bannerFile.toURI().toString());
        pettieImageView.setImage(pettieImage);
    }


    public void setSendButtonOnAction(ActionEvent event) {
        saveContactInfo();
        createCheckForm();
    }


    public void setCloseButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    private void saveContactInfo() {
        String newData = "\n";
        if (isInputValid()) {
            String firstname = firstNameTextField.getText();
            String lastname = lastNameTextField.getText();
            String phone = phoneTextField.getText();
            String mail = mailTextField.getText();
            String address = addressTextArea.getText();
            String subject = subjectTextArea.getText();

            contactInfo = new ContactInfo(firstname, lastname, phone, mail, address, subject);

            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("contact.bin", true))) {
                dos.writeUTF(newData);
                dos.writeUTF(contactInfo.getFirstName());
                dos.writeUTF(contactInfo.getLastName());
                dos.writeUTF(contactInfo.getPhoneNumber());
                dos.writeUTF(contactInfo.geteMail());
                dos.writeUTF(contactInfo.getAddress());
                dos.writeUTF(contactInfo.getSubject());
            } catch (IOException e) {
                e.printStackTrace();
            }

            closeContentForm();

        } else {
            showErrorDialog("Lütfen gerekli alanları doldurun.");
        }
    }

    public void createCheckForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckInfo.fxml"));
            Parent root = loader.load();
            CheckFormController checkFormController = loader.getController();
            checkFormController.setContactInfo(contactInfo);

            Stage checkStage = new Stage();
            Scene checkScene = new Scene(root, 600, 400);
            checkStage.setScene(checkScene);
            checkStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    private boolean isInputValid() {
        String firstname = firstNameTextField.getText();
        String mail = mailTextField.getText();
        String subject = subjectTextArea.getText();

        return !firstname.isEmpty() && !mail.isEmpty() && !subject.isEmpty();
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Please try again..");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeContentForm() {
        Stage stage = (Stage) firstNameTextField.getScene().getWindow();
        stage.close();
    }
}

