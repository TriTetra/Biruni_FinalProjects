package com.example.loginproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private ImageView shieldImageView;
    @FXML
    private Button returnButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label registrationLabelMessage;
    @FXML
    private Label passwordNotMatchMessage;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private Label warningLabel;

    // Kullanıcı burada resimlerin konumunu kendi bilgisayarındaki konuma göre girmeli...!!
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File shieldFile = new File("C:\\Users\\abdul\\OneDrive\\Masaüstü\\Java\\LoginProject\\src\\main\\resources\\Images\\shield.png");
        Image shieldImage = new Image(shieldFile.toURI().toString());
        shieldImageView.setImage(shieldImage);
    }

    public void returnButtonOnAction(ActionEvent event) {
        Main helloApplication = new Main();
        Stage stage = (Stage) returnButton.getScene().getWindow();
        helloApplication.start(stage);
        stage.show();
    }

    public void registerButtonOnAction() {
        if (isInputValid()) {
            if (Objects.equals(setPasswordField.getText(), confirmPasswordField.getText())) {
                registerUser();
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.close();
            } else {
                passwordNotMatchMessage.setText("Password Does not Match.!");
            }
        } else {
            warningLabel.setText("Please Fill in the Blanks..!");
        }
    }

    public void registerUser() {

        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectionDB = connectionNow.getConnection();

        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String userName = usernameTextField.getText();
        String eMail = emailTextField.getText();
        String password = setPasswordField.getText();

        String insertFields = "INSERT INTO author(first_name, last_name, username, password, email) VALUES('";
        String insertValues = firstName + "','" + lastName + "','" + userName + "','" + password + "','" + eMail + "')";
        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(insertToRegister);
            registerAlert();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isInputValid() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String userName = usernameTextField.getText();
        String eMail = emailTextField.getText();
        String password = setPasswordField.getText();
        return !(firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || eMail.isEmpty() || password.isEmpty());
    }

    public void registerAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Successfull");
        alert.setContentText("Your successfully registired to bank..");
        alert.setHeaderText("Registration Successfull..");
        alert.showAndWait();
    }
}
