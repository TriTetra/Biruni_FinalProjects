package com.example.loginproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class WorkInProgressController implements Initializable {

    @FXML
    private ImageView workInProgressView;
    @FXML
    private ImageView waitingView;
    @FXML
    private ImageView moveDownImageView;
    @FXML
    private ImageView exitImageView;

    // Kullanıcı burada resimlerin konumunu kendi bilgisayarındaki konuma göre girmeli...!!
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File workInProgressFile = new File("C:\\Users\\abdul\\OneDrive\\Masaüstü\\Java\\LoginProject\\src\\main\\resources\\Images\\work-in-progress.png");
        Image workInProgressImage = new Image(workInProgressFile.toURI().toString());
        workInProgressView.setImage(workInProgressImage);

        File waitingFile = new File("C:\\Users\\abdul\\OneDrive\\Masaüstü\\Java\\LoginProject\\src\\main\\resources\\Images\\wait.png");
        Image waitingImage = new Image(waitingFile.toURI().toString());
        waitingView.setImage(waitingImage);

        File moveDownFile = new File("C:\\Users\\abdul\\OneDrive\\Masaüstü\\Java\\LoginProject\\src\\main\\resources\\Images\\download.png");
        Image moveDownImage = new Image(moveDownFile.toURI().toString());
        moveDownImageView.setImage(moveDownImage);

        File fireExitFile = new File("C:\\Users\\abdul\\OneDrive\\Masaüstü\\Java\\LoginProject\\src\\main\\resources\\Images\\fire-exit.png");
        Image fireImage = new Image(fireExitFile.toURI().toString());
        exitImageView.setImage(fireImage);

        clicedImage();
    }


    public void clicedImage(){
        waitingView.setOnMouseClicked(event -> {
            // Pencereyi kapat
            Stage stage = (Stage) waitingView.getScene().getWindow();
            stage.close();
        });
    }
}
