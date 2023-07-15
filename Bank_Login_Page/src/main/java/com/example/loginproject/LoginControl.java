package com.example.loginproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginControl implements Initializable {

    @FXML
    private Button LG;
    @FXML
    private Label LGMessage;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView timeImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button closeMainWindow;
    @FXML
    private Button registerGoButton;

    // Kullanıcı burada resimlerin konumunu kendi bilgisayarındaki konuma göre girmeli...!!
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("C:\\Users\\abdul\\OneDrive\\Masaüstü\\Java\\LoginProject\\src\\main\\resources\\Images\\PiggyBankPower.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File timeFile = new File("C:\\Users\\abdul\\OneDrive\\Masaüstü\\Java\\LoginProject\\src\\main\\resources\\Images\\number.png");
        Image timeImage = new Image(timeFile.toURI().toString());
        timeImageView.setImage(timeImage);
    }

    public void LGMessageOnAction(ActionEvent event) {
        if (!usernameTextField.getText().isBlank() && !passwordField.getText().isBlank()) {
            validateLogin();
        } else {
            LGMessage.setText("Please enter username and password.");
        }
    }

    public void CloseWindowOnAction(ActionEvent event) {
        Stage stage = (Stage) closeMainWindow.getScene().getWindow();
        stage.close();
    }


    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM author WHERE username = '" + usernameTextField.getText() + "' AND password = '" + passwordField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    LGMessage.setText("Congratulations!");
                    goToLoadingPage();
                    Stage stage = (Stage) LG.getScene().getWindow();
                    stage.close();
                } else {
                    LGMessage.setText("Invalid Login. Try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void setRegisterGoButtonOnAction(ActionEvent event) {
        createAccountForm();
        Stage stage = (Stage) registerGoButton.getScene().getWindow();
        stage.close();
    }

    public void goToLoadingPage(){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loadingPage.fxml")));
            Stage registerStage = new Stage();
            Scene registerScene = new Scene(root);
            registerStage.getIcons().add(icon());
            registerStage.setTitle("Work in Progress");
            registerStage.setScene(registerScene);
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createAccountForm() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Register.fxml")));
            Stage registerStage = new Stage();
            Scene registerScene = new Scene(root);
            registerStage.setTitle("Registration");
            registerStage.getIcons().add(icon());
            registerStage.setScene(registerScene);
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public Image icon(){
        File iconFile = new File("C:\\Users\\abdul\\OneDrive\\Masaüstü\\Java\\LoginProject\\src\\main\\resources\\Images\\pig.png");
        return new Image(iconFile.toURI().toString());
    }
}
