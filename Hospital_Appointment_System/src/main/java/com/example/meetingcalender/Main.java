package com.example.meetingcalender;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
            stage.setTitle("Meeting Calendar");
            stage.getIcons().add(icon());
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

    public static void main(String[] args) {
        launch();
    }
}
